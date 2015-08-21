import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner {

    /**
     * args[0] = port, args[1] = class who's test to run
     */
    public static void main(String[] args){
        SocketThread socketThread = null;
        try{
            socketThread = new SocketThread(Integer.parseInt(args[0]));
        }catch(Exception e){
            System.err.println("Connection Failed: " + e);
            System.exit(0);
        }
        try{
            socketThread.start();
        
            Class testClass = Class.forName(args[1]);
            Result result = new JUnitCore().run(testClass);

            socketThread.sendOut("Test: " + result.getRunCount() + System.lineSeparator()
                             + "Fail: " + result.getFailureCount());
            for (Object fail : result.getFailures()) {
                socketThread.sendOut("    " + fail);
            }        
        }catch(Exception e){
            socketThread.sendOut("Program Error: " + e);
        }
        socketThread.close();
    }
}