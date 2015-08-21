package Runner;

import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class RunnerThread extends Thread{

    private final ResultHolder holder;
    private final ProcessBuilder bldr;
    
    private ServerSocket serverSocket;
    private Socket socket;
    private Process proc;
    
    public RunnerThread(ResultHolder h, ServerSocket ss, ProcessBuilder b){
        holder = h;
        serverSocket = ss;
        bldr = b;
    }
    
    public void stopTest(){
        try{
            socket.close();
            proc.destroy();
        }catch(Exception e){
            System.err.println("Err: " + e);
        }
    }
    
    @Override
    public void run(){
        String resultData = "";
        try{
            proc = bldr.start();
            
            Socket socket = serverSocket.accept();
            serverSocket.close();
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String msg;
            while ((msg = socketIn.readLine()) != null) {
                resultData += msg + System.lineSeparator();
            }
            holder.setData(resultData);
        }catch(Exception ex){
            holder.setData("Exception: " + ex + " <"+ resultData+">");
        }
    }
}
