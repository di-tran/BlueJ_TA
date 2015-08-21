
import java.io.File;
import bluej.extensions.*;
import java.awt.Frame;
import Runner.*;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * Write a description of class Exercise here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

@XmlRootElement
public class Exercise {
    private String projName;
    public File testFile = null; 
    public BPackage exercisePkg = null; 
    
    /*------*/
    
    private String title, description, hint, sampleAnswer, javaName, javaCode, junitName, junitCode;
    private List<String> example;
    
    public Exercise() {
        title=null;
        description=null;
        hint=null;
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
    public String getTitle(){
        return title;
    }
    
    @XmlElement
    public String getDescription(){
        return description;
    }
    
    @XmlElement
    public String getHint(){
        return hint;
    }
    
    @XmlElement
    public String getSampleAnswer(){
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
    
    @XmlElementWrapper
    @XmlElement
    public List<String> getExample() {
        return example;
    }
    
    public void printExercise(){
        System.out.println("Title: "+title);
        System.out.println("Description: "+description);
        System.out.println("Hint: "+hint);
        System.out.println("Sample Answer: "+sampleAnswer);
        for(int x=0; x<example.size(); x++)
            System.out.println("Example "+x+": "+example.get(x));
        System.out.println("Java Name: "+javaName);
        System.out.println("Java Code: "+javaCode);
        System.out.println("Junit Name: "+junitName);
        System.out.println("Junit Code: "+junitCode);
    }
    
    public void launch() {
        BlueJ bluej = StateManager.blueJ;
        FileUtil.closeProj(bluej, projName);
        File exerciseTempDir = new File(StateManager.getTempDir(), projName + "_Files");
        FileUtil.deleteDir(exerciseTempDir);
        exerciseTempDir.mkdir();
        File projDir = new File(exerciseTempDir, projName);
        BProject bproj = bluej.newProject(projDir);

        Frame frame = null;
        
        //changed usercodename to javaname
        //changed testcodename to junitname
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
            bTestClass.remove();
            
            exercisePkg = bpkg;
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
        if (frame != null) {
            frame.getComponent(0).setVisible(true);
            frame.toFront();
        }
    }

    public String execute(){
        if(!isLaunched()){
            return "Exercise not Launched";
        }
        String msg;
        try{
            File excDir = exercisePkg.getDir();
            ResultHolder holder = StateManager.getRunner().runTest(excDir, testFile);
            holder.waitForResults(); //put wait for results flag in? (out of scope)
            msg = holder.getData();
        }catch(Exception e){
            msg = e.getMessage();
        }
        return msg;        
    }
    
    public boolean isLaunched(){
        return testFile != null && exercisePkg != null; 
    }
    
    public String toString() {
        return this.title;
    }
}