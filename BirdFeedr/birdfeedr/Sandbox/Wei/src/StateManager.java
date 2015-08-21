import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.io.File;
import org.junit.runner.Result;
import java.io.*;
import javax.swing.*;


/**
 * Write a description of class StateManager here.
 * 
 * @author Miguel Roman-Roman
 * @author Wei Huang
 * @author Di Tran
 * @author Josh Gillham
 * @author Nathan Witt
 * @author Thomas Macari
 * @version (03/21/2015)
 */
public class StateManager
{
    /** The current exercise chosen. */
    public static Exercise currentExercise = null;
    public static TestResultsGUI testResultsGUI = null;
    public static DescriptionGUI descriptionGUI = null;
    public static ExerciseListGUI exerciseListGUI = null;
    private static ArrayList<Exercise> exercises = null;
    private static StateManager instance = null;
    
    /**
     * 
     */
    private StateManager() {
    }

    /**
     * 
     */
    public static void parseExercises() {
    	/* Will parse xml files and populate exercise list.
    	 * March 28, 2015 last edited comment.
    	 */
    	initialize();
    }
   
    
    /**
     * 
     */
    private static void initialize() {
        exercises =  new ArrayList<Exercise>(25);
        String desc = "", ex = "";
        for(int i = 0; i < 300; i++) {
            desc = desc + "z";
            ex = ex + "a";
        }
        //temporary
        exercises.add(new Exercise("Fib" , desc, ex));
        for(int i = 1; i < 25; i++) {
            exercises.add(new Exercise("Exercise: " + i, "desc", "example"));
        }
        
    }
    
     /**
     * 
     */
    public static StateManager getInstance() {
        if (instance == null){
            instance = new StateManager();
            initialize();
        }
        
        return instance;
    }
    
     /**
     *  void?
     */
    public static Result runExerciseTests() {
        //exercise needs to hold its location
        
        Interface tester = new Interface();
        File file = new File("TestClass.class");
        return tester.test(file);
    }
    
    /**
     * Initializes the exercise list.
     * @return exercises - the list of exercises.
     */
    public static ArrayList<Exercise> getExerciseList() {
        if(exercises == null) {
            initialize();
        }
        return exercises;
    }
    
    /**
     * Creates the GUI showing the JUnit tests for the exercise.
     */
    public static void createTestResultsGUI() {
        if (testResultsGUI == null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    testResultsGUI = new TestResultsGUI();
                    testResultsGUI.packGUI();
                }
            });
        } else if(!testResultsGUI.isVisible()) {
            testResultsGUI.setVisible(true);
        }
    }
    
    /**
     * creates the GUI for the description of the exercise.
     */
    public static void createDescriptionGUI() {
        if (descriptionGUI == null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    descriptionGUI = new DescriptionGUI();
                    descriptionGUI.packGUI();
                }
            });
        } else if(!descriptionGUI.isVisible()) {
            descriptionGUI.setVisible(true);
        }
    }
    
    /**
     * Creates the GUI for picking an exercise.
     */
    public static void createExerciseListGUI() {
        if (exerciseListGUI == null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    exerciseListGUI = new ExerciseListGUI();
                    exerciseListGUI.packGUI();
                }
            });
        } else if(!exerciseListGUI.isVisible()) {
            exerciseListGUI.setVisible(true);
        }
    }
    
    /**
     * disposes testGUI and descriptionGUI
     */
    public static void disposeDescriptionGUI() {
        if( descriptionGUI != null ){
            descriptionGUI.setVisible(false);
            descriptionGUI.dispose();
            descriptionGUI = null;
        }
    }
    
     /**
     * disposes testGUI and descriptionGUI
     */
    public static void disposeTestResultsGUI() {
        if( testResultsGUI != null ){
            testResultsGUI.setVisible(false);
            testResultsGUI.dispose();
            testResultsGUI = null;
        }
    }
}
