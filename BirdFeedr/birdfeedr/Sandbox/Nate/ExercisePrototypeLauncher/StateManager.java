import bluej.extensions.*;
import java.io.File;

import Runner.*;
import TempStuff.*;

public class StateManager {
  
    private static BlueJ bluej;
    private static JUnitRunner runner;
    private static File tempDir;
    
    private static Exercise exercise;
    
    public static void setUp(BlueJ b){
        bluej = b;
        tempDir = new File(bluej.getUserConfigDir(), "CodingBatTemp");
        FileUtil.deleteDir(tempDir);
        tempDir.mkdir();
        runner = new JUnitRunner(bluej);
    }
    
    public static BlueJ getBlueJ(){
        return bluej;
    }
    
    public static File getTempFileDir(){
        return tempDir;
    }
    
    public static JUnitRunner getRunner(){
        return runner;
    }
    
    public static void setDefaultExercise(){
        String projName = RawExerciseData.projName; 
        String userTemplateCode = RawExerciseData.userTemplateCode; 
        String testCode = RawExerciseData.testCode; 
                            
        exercise = new Exercise(projName, userTemplateCode, testCode);
    }
    
    public static Exercise getExercise(){
        return exercise;
    }
}
