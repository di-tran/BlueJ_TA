package Extension.BackEnd;

import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.io.File;
import org.junit.runner.Result;
import java.io.*;
import javax.swing.*;
import java.nio.file.Path;
import bluej.extensions.BlueJ;
import bluej.extensions.BProject;
import bluej.extensions.BPackage;
import bluej.extensions.BClass;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

import Extension.Main;
import Extension.GUI.*;

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
public class StateManager {
    private static BlueJ blueJ;
    
    private static String exercisesLocation;
    private static ArrayList<Exercise> exercises;
    private static Exercise currentExercise;
    
    private static TestResultsGUI testResultsGUI;
    private static DescriptionGUI descriptionGUI;
    private static ExerciseListGUI exerciseListGUI;
    
    private static File tempDir, extDir;
    
    /**
     * Private constructor
     */
    private StateManager() {
    }
    
    public static void setup(BlueJ bluej){
        blueJ = bluej;
        tempDir = new File(blueJ.getUserConfigDir(), "TA_Temp");
        FileUtil.deleteDir(tempDir);
        tempDir.mkdir();
        
        StateManager.parseExercises();
    }
    
    public static BlueJ getBlueJ(){
        return blueJ;
    }
    
    public static File getTempDir(){
        return tempDir;
    }
    
    public static Exercise getCurrentExercise(){
        return currentExercise;
    }
    
    public static void setCurrentExercise(Exercise ex){
        currentExercise = ex;
    }
    
    public static String getExercisesLocation(){
        return exercisesLocation;
    }
    
    public static void setExercisesLocation(String location){
        exercisesLocation = location;
    }
    
    public static TestResultsGUI getTestResultsGUI(){
        return testResultsGUI;
    }
    
    public static DescriptionGUI getDescriptionGUI(){
        return descriptionGUI;
    }
    
    public static ExerciseListGUI getExerciseListGUI(){
        return exerciseListGUI;
    }
    
    public static void setTestResultsGUI(TestResultsGUI gui){
        testResultsGUI = gui;
    }
    
    public static void setDescriptionGUI(DescriptionGUI gui){
        descriptionGUI = gui;
    }
    
    public static void setExerciseListGUI(ExerciseListGUI gui){
        exerciseListGUI = gui;
    }
    
    /**
     * Initializes the exercise list.
     * @return exercises - the list of exercises.
     */
    public static ArrayList<Exercise> getExerciseList() {
        return exercises;
    }
    
    public static void showErrorMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void parseExercises() {
        exercises = new ArrayList<Exercise>();
        System.out.println( exercisesLocation );
        if (exercisesLocation == null || exercisesLocation.isEmpty()){
            String listDir = "/Extension/exercises/exerciseList.lst"; 
            Scanner in = new Scanner(Main.class.getResourceAsStream(listDir));
            XMLReader reader = new XMLReader();
            while (in.hasNextLine()){
                String ex = in.nextLine();
                System.out.println("ex: " + ex);
                exercises.add(reader.readExercise(Main.class.getResourceAsStream(ex)));
            }
        }else{
            File directory = new File(exercisesLocation);
            System.out.println(directory.toPath());
            File[] filesInDir = directory.listFiles();
            if(filesInDir != null){
                XMLReader reader = new XMLReader();
                for(int i = 0; i < filesInDir.length; i++) {    
                    if(filesInDir[i].isFile() && !filesInDir[i].isHidden()){
                        System.out.println("File: " + filesInDir[i].toPath().toString());
                        exercises.add(reader.readExercise(filesInDir[i].toPath().toString()));
                    }
                }
            }
        }
        System.out.println("Size: " + exercises.size());
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
     * disposes descriptionGUI
     */
    public static void disposeDescriptionGUI() {
        if( descriptionGUI != null ){
            descriptionGUI.setVisible(false);
            descriptionGUI.dispose();
            descriptionGUI = null;
        }
    }
    
     /**
     * disposes testGUI 
     */
    public static void disposeTestResultsGUI() {
        if( testResultsGUI != null ){
            testResultsGUI.setVisible(false);
            testResultsGUI.dispose();
            testResultsGUI = null;
        }
    }
}
