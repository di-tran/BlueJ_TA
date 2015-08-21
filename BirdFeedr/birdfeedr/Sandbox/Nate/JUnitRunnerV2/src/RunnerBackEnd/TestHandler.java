package RunnerBackEnd;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Handles the TestRunner process and notifies the TestListerns of the results
 *
 * @author Nathan
 */
class TestHandler extends Thread {

    /**
     * Location of the java.exe to start the TestRunner with.
     */
    private final static String JAVA_DIR = System.getProperty("java.home") + "/bin/java";

    /**
     * A reference to the parent Builder to notify TestListerns with.
     */
    private TestBuilder parentBuilder;
    /**
     * The SocketThread to communicate to the TestRunner process
     */
    private SocketThread socketThread;
    /**
     * Used to indicate if test results should be delivered after a test is done
     * or not.
     */
    private boolean deliverResults;
    /**
     * The JUnit class file to execute
     */
    private File classFile;

    TestHandler(TestBuilder pr, File cf) {
        parentBuilder = pr;
        classFile = cf;
    }

    /**
     * Starts the TestRunner and delivers its results after completion
     */
    @Override
    public void run() {
        deliverResults = true;
        String results;
        try {
            startRunner();
            parentBuilder.notifyStarted();

            synchronized (socketThread) {
                socketThread.wait();//wait for runner to close
            }

            if (deliverResults) {
                results = socketThread.readInAll();
            } else {
                results = "Test Cancelled";
            }
        } catch (Exception e) {
            results = "Test Cancelled by: " + e;
            stopTest();
        }
        classFile.delete();
        parentBuilder.notifyCompletion(results);
    }

    /**
     * Starts the TestRunner process and sets up the SocketThread to communicate
     * with it.
     *
     * @throws IOException if the socket failed to connect
     */
    private void startRunner() throws IOException {
        ServerSocket serverSocket = new ServerSocket(0, 0, InetAddress.getByName(null));
        ProcessBuilder bldr = new ProcessBuilder(
                JAVA_DIR, "-cp", "JUnitRunner.jar",
                "RunnerBackEnd.TestRunner", 
                serverSocket.getLocalPort() + "",
                classFile.getAbsolutePath());
        bldr.redirectOutput(ProcessBuilder.Redirect.INHERIT);//gets out printlns to read errs
        bldr.start();

        socketThread = new SocketThread(serverSocket.accept());
        socketThread.start();

        serverSocket.close();
    }

    /**
     * Stops the running test
     */
    public void stopTest() {
        deliverResults = false;
        try {
            socketThread.close();
        } catch (Exception e) {
        }
    }
}
