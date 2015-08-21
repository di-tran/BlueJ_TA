import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Socket Thread, tends to the input and output of a socket.
 *
 * @author Nathan
 */
class SocketThread extends Thread {

    private final Socket socket;
    private final BufferedReader socketIn;
    private final PrintWriter socketOut;

    /**
     * Message buffer to store unread incoming messages
     */
    private final ArrayList<String> msgs = new ArrayList<String>();

    SocketThread(int port) throws IOException {
        this(new Socket((String) null, port));
    }

    SocketThread(Socket s) throws IOException {
        socket = s;
        socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOut = new PrintWriter(socket.getOutputStream(), true);
    }

    /**
     * Reads incoming messages and stores them in the message buffer. Then
     * notifies all objects waiting on this thread when it exits.
     */
    @Override
    public void run() {
        try {
            String msg;
            while ((msg = socketIn.readLine()) != null) {
                synchronized (msgs) {
                    msgs.add(msg);
                }
            }
        } catch (Exception e) {
            System.err.println("SocketThread err: " + e);
        }
        synchronized (this) {
            notifyAll();
        }
    }

    /**
     * Removes and returns the first message in the message buffer.
     *
     * @return the message from the message buffer, null if the buffer is empty.
     */
    public String readIn() {
        synchronized (msgs) {
            return msgs.remove(0);
        }
    }

    /**
     * Removes and returns all the messages in the message buffer as one string.
     *
     * @return the messages from the message buffer.
     */
    public String readInAll() {
        synchronized (msgs) {
            String allMsgs = "";
            for (String msg : msgs) {
                allMsgs += msg + System.lineSeparator();
            }
            msgs.clear();
            return allMsgs;
        }
    }

    /**
     * Sends the message out through this threads underlying socket.
     *
     * @param msg the message to send.
     */
    public void sendOut(String msg) {
        socketOut.println(msg);
    }

    /**
     * Closes this threads underlying socket, causing this thread to terminate.
     */
    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
        }
    }
}
