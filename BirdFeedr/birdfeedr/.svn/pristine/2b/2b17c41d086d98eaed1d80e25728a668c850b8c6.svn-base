package Extension.BackEnd.Runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Executes a junit test and sends the results over a socket.
 *
 * @author Nathan
 */
public class TestRunner {

    /**
     * Executes the junit test located in the test file specified by args[1] and args[2], and
     * sends the results over a socket back to the parent process.
     *
     * @param args the port of the parent process, the directory of the test file, and the name 
     * of the junit test file to run.
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
            //Class testClass = Class.forName(args[1]);
            //args[1] = C:\Users\Nathan\bluej\TA_Temp\Permutations Exercise_Files
            //args[2] = PermutationTest
            
            File testFileDir = new File(args[1]);
            URL[] cp = {testFileDir.toURI().toURL()};
            URLClassLoader urlcl = new URLClassLoader(cp);
            Class testClass = urlcl.loadClass(args[2]);
            
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
            //socketOut.println("Current CP: " + System.getProperty("java.class.path"));
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
