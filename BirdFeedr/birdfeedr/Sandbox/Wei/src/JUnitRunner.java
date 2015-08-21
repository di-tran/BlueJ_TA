 

import java.io.File;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * JUnit Runner
 */
public class JUnitRunner
{
 
    /**
     * Runs Junit Test from specified file, and returns the results object containing the results.
     * 
     * @param testFile the JUnit test class to run
     * @Returns the result of the JUnit Test
     */
    public static Result runTest(File testFile) {
        try {
            Class testClass = Class.forName(testFile.getName().split("\\.")[0]);
            Result result = new JUnitCore().run(testClass);
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}