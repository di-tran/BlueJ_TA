package Runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


import java.net.Socket;
import java.io.PrintWriter;
import java.io.InputStream;

public class TestRunner {
    
    /**
     * args[0] = port, args[1] = class who's test to run
     */
    public static void main(String[] args){
        Socket socket = null;
        PrintWriter socketOut = null;
        try{
            socket = new Socket((String) null, Integer.parseInt(args[0]));
            socketOut = new PrintWriter(socket.getOutputStream(), true);
        }catch(Exception e){
            System.err.println("Connection Failed: " + e);
            System.exit(0);
        }

        ExitThread exitThread = new ExitThread(socket);//foces this process to exit if parent crashes
        exitThread.setDaemon(true);
        exitThread.start();
        
        try{
            Class testClass = Class.forName(args[1]);
            Result result = new JUnitCore().run(testClass);

            int fails = result.getFailureCount();
            if(fails == 0){
                socketOut.println("All test Passed!");
            }else{
                for (Failure fail : result.getFailures()) {
                    socketOut.println(fail.getMessage());
                }        
            }
            
            //socketOut.println("Test: " + result.getRunCount());
            //int fails = result.getFailureCount();
            //socketOut.println("Fail: " + result.getFailureCount());
            //
            //for (Failure fail : result.getFailures()) {
            ///     socketOut.println(fail.getMessage());
            //}        
            socket.close();
        }catch(Exception e){
            socketOut.println("Program Error: " + e);
        }
    }
    
    private static class ExitThread extends Thread{
        
        private Socket socket;
        
        private ExitThread(Socket s){
            socket = s;
        }
        
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