import bluej.extensions.*;
import java.awt.Frame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.File;

import Runner.ResultHolder;

public class Exercise {
    private final String PROJ_NAME;
    
    private final String USER_CODE_NAME;
    private final String USER_TEMPLATE;
    
    private final String TEST_CODE_NAME;
    private final String TEST_CODE;

    private File testFile = null; 
    private BPackage exercisePkg = null; 
    
    public Exercise(String projName, String userTemplateCode, String testCode){
        PROJ_NAME = projName;
        USER_TEMPLATE = userTemplateCode;
        TEST_CODE = testCode;
        
        USER_CODE_NAME = FileUtil.getName(USER_TEMPLATE);
        TEST_CODE_NAME = FileUtil.getName(TEST_CODE);
    }
    
    public JMenuItem getMenuItem(BClass bClass){
        if(!isValid(bClass)){
           return null; 
        }
        JMenuItem item = new JMenuItem(new AbstractAction("Test"){
            public void actionPerformed(ActionEvent e) {
                new Thread(){
                    public void run(){
                        String msg = execute();
                        JOptionPane.showMessageDialog(StateManager.getBlueJ().getCurrentFrame(), msg);   
                    }
                }.start();
            }
        }); 
        return item;
    }
    
    private boolean isValid(BClass bClass){
        return testFile != null
               && StateManager.getBlueJ().getCurrentPackage() == exercisePkg 
               && bClass.getName().equals(USER_CODE_NAME);
    }
    
    public void launch() {
        FileUtil.closeProj(StateManager.getBlueJ(), PROJ_NAME);
        File exerciseTempDir = new File(StateManager.getTempFileDir(), PROJ_NAME + "_Files");
        FileUtil.deleteDir(exerciseTempDir);
        exerciseTempDir.mkdir();
        File projDir = new File(exerciseTempDir, PROJ_NAME);
        BProject bproj = StateManager.getBlueJ().newProject(projDir);

        Frame frame = null;
        try {
            BPackage bpkg = bproj.getPackage("");

            frame = bpkg.getFrame();
            frame.getComponent(0).setVisible(false);//frame.setEnabled(false);

            FileUtil.save(new File(projDir, USER_CODE_NAME + ".java"), USER_TEMPLATE);
            FileUtil.save(new File(projDir, TEST_CODE_NAME + ".java"), TEST_CODE);

            bpkg.newClass(USER_CODE_NAME);
            BClass bTestClass = bpkg.newClass(TEST_CODE_NAME);
            bpkg.compileAll(true);

            testFile = new File(exerciseTempDir, TEST_CODE_NAME + ".class");
            FileUtil.copy(bTestClass.getClassFile(), testFile);
            bTestClass.remove();
            
            exercisePkg = bpkg;
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
        if (frame != null) {
            frame.getComponent(0).setVisible(true);
            frame.toFront();
            //frame.setEnabled(true);
        }
    }
    
    public boolean isLaunched(){
        return testFile != null && exercisePkg != null; 
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
}