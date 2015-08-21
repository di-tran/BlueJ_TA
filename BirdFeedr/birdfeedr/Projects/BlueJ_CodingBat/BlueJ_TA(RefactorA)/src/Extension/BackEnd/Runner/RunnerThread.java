package Extension.BackEnd.Runner;

import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * A thread that handles starting and gathering the results from a TestRunner
 * process.
 *
 * @author Nathan
 */
class RunnerThread extends Thread {

    /**
     * The result holder to send the result of the test to.
     */
    private final ResultHolder holder;

    /**
     * The builder that has the TestRunner process parameters set within it, to
     * be started.
     */
    private final ProcessBuilder bldr;

    /**
     * The Server Socket that will establish communication with the TestRunner
     * process.
     */
    private ServerSocket serverSocket;

    /**
     * The socket to gather the results from the TestRunner process.
     */
    private Socket socket;

    /**
     * The handle to the TestRunner process.
     */
    private Process proc;

    /**
     * Creates an setups a Runner thread
     *
     * @param h the holder to pass the results to
     * @param ss the Server Socket to connect with the TestRunner process.
     * @param b a process builder setup to run the TestRunner main class.
     */
    RunnerThread(ResultHolder h, ServerSocket ss, ProcessBuilder b) {
        holder = h;
        serverSocket = ss;
        bldr = b;
    }

    /**
     * Stops the currently running junit test. Any result holders waiting for
     * results will be populated with cancel messages.
     */
    public void stopTest() {
        try {
            socket.close();
            proc.destroy();
        } catch (Exception e) {
            System.err.println("Err: " + e);
        }
    }

    /**
     * Starts up the TestRunner process to execute the junit test and gathers
     * its results to be sent to the result holder.
     */
    @Override
    public void run() {
        String resultData = "";
        try {
            proc = bldr.start();

            Socket socket = serverSocket.accept();
            serverSocket.close();
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg;
            while ((msg = socketIn.readLine()) != null) {
                resultData += msg + System.lineSeparator();
            }
            holder.setData(resultData);
        } catch (Exception ex) {
            holder.setData("Exception: " + ex + " <" + resultData + ">");
        }
    }
}
