package Extension.BackEnd;

import bluej.extensions.*;
import java.awt.Frame;
import java.util.List;
import javax.xml.bind.annotation.*;

import java.net.*;
import java.io.*;


/**
 * 
 * @author
 */
@XmlRootElement
public class Exercise {

    /**
     * The junit test file this exercise will use to execute test.
     */
    private File testFile = null;
    
    /**
     * The bluej package this exercise is associated with
     */
    private BPackage exercisePkg = null;

    private String title, description, hint, sampleAnswer, javaName, javaCode, junitName, junitCode;
    private List<String> example;

    public Exercise() {
    }

    public Exercise(String title, String description, String hint) {
        this.title = title;
        this.description = description;
        this.hint = hint;
    }

    @XmlElement
    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    @XmlElement
    public String getJavaCode() {
        return javaCode;
    }

    public void setJavaCode(String javaCode) {
        this.javaCode = javaCode;
    }

    @XmlElement
    public String getJunitName() {
        return junitName;
    }

    public void setJunitName(String junitName) {
        this.junitName = junitName;
    }

    @XmlElement
    public String getJunitCode() {
        return junitCode;
    }

    public void setJunitCode(String junitCode) {
        this.junitCode = junitCode;
    }

    public void setExampleList(List<String> exampleList) {
        this.example = exampleList;
    }

    @XmlElement
    public String getTitle() {
        return title;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public String getHint() {
        return hint;
    }

    @XmlElement
    public String getSampleAnswer() {
        return sampleAnswer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setSampleAnswer(String sampleAnswer) {
        this.sampleAnswer = sampleAnswer;
    }

    public void setExample(List<String> example) {
        this.example = example;
    }

    @XmlElementWrapper(name = "examples")
    @XmlElement(name = "example")
    public List<String> getExample() {
        return example;
    }

    public void printExercise() {
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Hint: " + hint);
        System.out.println("Sample Answer: " + sampleAnswer);
        for (int x = 0; x < example.size(); x++) {
            System.out.println("Example " + x + ": " + example.get(x));
        }
        System.out.println("Java Name: " + javaName);
        System.out.println("Java Code: " + javaCode);
        System.out.println("Junit Name: " + junitName);
        System.out.println("Junit Code: " + junitCode);
    }

    /**
     * Launches this exercise by setting up the junit test and user project.
     */
    public void launch() {
        BlueJ bluej = StateManager.getBlueJ();
        FileUtil.closeProj(bluej, title);
        File exerciseTempDir = new File(StateManager.getTempDir(), title + "_Files");
        FileUtil.deleteDir(exerciseTempDir);
        exerciseTempDir.mkdir();
        File projDir = new File(exerciseTempDir, title);
        BProject bproj = bluej.newProject(projDir);

        Frame frame = null;

        try {
            BPackage bpkg = bproj.getPackage("");

            frame = bpkg.getFrame();
            frame.getComponent(0).setVisible(false);

            FileUtil.save(new File(projDir, javaName + ".java"), javaCode);
            FileUtil.save(new File(projDir, junitName + ".java"), junitCode);

            bpkg.newClass(javaName);
            BClass bTestClass = bpkg.newClass(junitName);

            bpkg.compileAll(true);

            testFile = new File(exerciseTempDir, junitName + ".class");
            FileUtil.copy(bTestClass.getClassFile(), testFile);
            
            exercisePkg = bpkg;
            
            bTestClass.remove();
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
        if (frame != null) {
            frame.getComponent(0).setVisible(true);
            frame.toFront();
        }
    }

    /**
     * Executes this exercise and returns the results
     *
     * @return the results of this Exercise.
     */
    public String execute() {
        if (!isLaunched()) {
            return "Exercise not Launched";
        }
        
        final Frame frame;
        try {
            frame = exercisePkg.getFrame(); 
        } catch (Exception e) {
            return e.toString();
        }
        String msg;
        try { 
            File userProjDir = exercisePkg.getDir();
            File projTestFile = new File(userProjDir, testFile.getName());
            FileUtil.copy(testFile, projTestFile);
            FileUtil.extractResource("runner/" ,"JRunner.class", userProjDir);
            
            frame.getComponent(0).setVisible(false);
            exercisePkg.reload();
            
            BClass testClass = exercisePkg.getBClass(junitName);
            BClass runClass = exercisePkg.getBClass("JRunner");

            BMethod resetMethod = runClass.getMethod("resetRunning", null);
            resetMethod.invoke(null, null);
            
            BMethod runMethod = runClass.getMethod("run", new Class<?>[]{String.class});
            BField runFlag = runClass.getField("running");
            BObject runRef = runClass.getConstructor(null).newInstance(null);
  
            new Thread(){
                public void run(){
                    try {
                        while(!(Boolean)runFlag.getValue(runRef)){
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        System.out.println("Err: "+e);
                    }
                    try {
                        testClass.remove();
                    } catch (Exception e) {
                        System.out.println("Err: "+e);
                    }   
                    try {
                        runClass.remove();
                    } catch (Exception e) {
                        System.out.println("Err: "+e);
                    }
                    frame.getComponent(0).setVisible(true);
                }
            }.start();
            
            Object results = runMethod.invoke(null, new Object[]{junitName});
            msg = results.toString();
        } catch (InvocationErrorException e){
            msg = "Test Canceled by user.";
        } catch (Exception e) {
            msg = e.toString();
        }
        return msg;
    }

    /**
     * Test if this exercise is currenly able up to run test
     */
    public boolean isLaunched() {
        return testFile != null && exercisePkg != null;
    }

    public String toString() {
        return this.title;
    }
}
