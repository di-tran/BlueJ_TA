import bluej.extensions.*;
import bluej.extensions.editor.*;

import bluej.extensions.event.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;

class DemoMenuBldr extends MenuGenerator {
    
    private final BlueJ bluej;
    
    private final JUnitRunner runner;
    
    private File junitClassFile;
    
    DemoMenuBldr(BlueJ b){
        bluej = b;
        runner = new JUnitRunner(bluej.getSystemLibDir().getAbsolutePath() + "/*",
                                 ExtPathGen.genExtPaths(bluej, "*"));
    }
    
    @Override
    public JMenuItem getToolsMenuItem(BPackage aPackage) {
        return new JMenuItem(new AbstractAction("Set JUnit Class File"){
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                        junitClassFile = chooser.getSelectedFile();
                        JOptionPane.showMessageDialog(null,"Test set: " + junitClassFile);
                    }
                }   
            });
    }
    
    @Override
    public JMenuItem getClassMenuItem(final BClass bClass) {
        JMenu menu = new JMenu("Exercise");
        try{
            final File excDir = bluej.getCurrentPackage().getDir();
            menu.add(new JMenuItem(new AbstractAction("Test"){
                public void actionPerformed(ActionEvent e) {
                    new Thread(){
                        public void run(){
                            try{
                                ResultHolder holder = runner.runTest(bluej.getCurrentPackage().getDir(),
                                                             junitClassFile);
                                holder.waitForResults();    
                                JOptionPane.showMessageDialog(null,holder.getData());
                            }catch(Exception ex){
                                System.out.println("Error: " + e);
                            }
                        }
                    }.start();
                }
            })); 
        }catch(Exception e){
            System.err.println("Err: " + e);
        }
        return menu;
    }
}