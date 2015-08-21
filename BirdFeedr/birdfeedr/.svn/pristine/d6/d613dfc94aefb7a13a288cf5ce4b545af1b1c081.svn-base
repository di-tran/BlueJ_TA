package Extension.BackEnd.Runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.InputStream;

/**
 * Executes a junit test and sends the results over a socket.
 *
 * @author Nathan
 */
public class TestRunner {

    /**
     * Executes the junit test located in the test file specified by args[1] and
     * sends the results over a socket back to the parent process.
     *
     * @param args the port of the parent process and the name of the junit test
     * file to run.
     */
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter socketOut = null;
        try {
            socket = new Socket((String) null, Integer.parseInt(args[0]));
            socketOut = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            System.err.println("Connection Failed: " + e);
            System.exit(0);
        }

        ExitThread exitThread = new ExitThread(socket);
        exitThread.setDaemon(true);
        exitThread.start();

        try {
            Class testClass = Class.forName(args[1]);
            Result result = new JUnitCore().run(testClass);

            int fails = result.getFailureCount();
            if (fails == 0) {
                socketOut.println("All test Passed!");
            } else {
                for (Failure fail : result.getFailures()) {
                    socketOut.println(fail.getMessage());
                }
            }
            socket.close();
        } catch (Exception e) {
            socketOut.println("Program Error: " + e);
        }
    }

    /**
     * A Thread designed to stop this process if communication with the parent
     * socket closes, ie the parent process crashes.
     */
    private static class ExitThread extends Thread {

        /**
         * The socket linked back to the parent process.
         */
        private Socket socket;

        /**
         * Sets up the Exit Thread
         *
         * @param s socket linked back to the parent process.
         */
        private ExitThread(Socket s) {
            socket = s;
        }

        /**
         * Causes this process to stop if the socket looses connection to the
         * parent.
         */
        @Override
        public void run() {
            try {
                InputStream in = socket.getInputStream();
                while (in.read() != -1);
            } catch (Exception e) {
            }
            System.exit(-1);
        }
    }
}
