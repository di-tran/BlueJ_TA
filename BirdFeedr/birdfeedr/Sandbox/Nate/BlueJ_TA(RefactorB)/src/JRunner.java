import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class JRunner {
  
    public static boolean running = false;

    public static void resetRunning(){
        running = false;
    }
    
    public static String run(String testClass){
        Class testC = null;
        try{
            testC = Class.forName(testClass);
        }catch(Exception e){
            return e.getMessage();
        }
        
        //send signal here
        running = true;
        
        Result result = new JUnitCore().run(testC);
        int fails = result.getFailureCount();
        String msg = "";
        if (fails == 0) {
            msg = "All test Passed!";
        } else {
            for (Failure fail : result.getFailures()) {
                msg += fail.getMessage()+ "\n";
            }
        }
        return msg;
    }
}