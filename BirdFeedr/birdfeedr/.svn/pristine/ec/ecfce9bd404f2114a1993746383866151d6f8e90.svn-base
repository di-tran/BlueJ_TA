package RunnerBackEnd;

import java.io.File;
import java.io.IOException;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Test Runner, runs the JUnit test class files
 *
 * @author Nathan
 */
public class TestRunner {

    /**
     * The SocketThread to communicate back to the TestHandler thread
     */
    private static SocketThread socketThread;

    /**
     * Used by TestHandler to run a test in its own process.
     *
     * @param args the port to connect with and the class file to load
     * @throws IOException if the socket failed to close
     */
    public static void main(String[] args) throws IOException {
        File classFile = new File(args[1]);
        classFile.deleteOnExit();//delete file on exit in case parent closes without deleting it
        try {
            socketThread = new ExitingSocketThread(args[0]);
            socketThread.start();

            Class testClass = CodeUtil.loadClass(classFile);
            Result results = new JUnitCore().run(testClass);
            sendResults(results);

        } catch (Exception e) {
            System.out.println(e);
        }
        socketThread.close();
    }

    /**
     * Sends the results back to the TestHandler via a socket
     *
     * @param result the result to send back
     */
    private static void sendResults(Result result) {
        socketThread.sendOut("Tests: " + result.getRunCount() + "\n"
                + "Fails: " + result.getFailureCount());
        for (Object fail : result.getFailures()) {
            socketThread.sendOut("    " + fail);
        }
    }

    /**
     * Custom Socket thread that will exit this process when finished.
     */
    private static class ExitingSocketThread extends SocketThread {

        public ExitingSocketThread(String port) throws IOException {
            super(Integer.parseInt(port));
        }

        @Override
        public void run() {
            super.run();
            System.exit(0);
        }
    }
}
