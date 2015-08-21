import bluej.extensions.*;
import bluej.extensions.editor.*;

import bluej.extensions.event.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.File;

class MenuBldr extends MenuGenerator {
    
    private final BlueJ bluej;
    
    MenuBldr(BlueJ b){
        bluej = b;
        ExecuteThread.setJunitPath(bluej.getSystemLibDir().getAbsolutePath() + "/*");
        ExecuteThread.setExtPath(ExtPathGen.gen(bluej, "*"));
    }
    
    @Override
    public JMenuItem getToolsMenuItem(BPackage aPackage) {
        return new JMenuItem(new AbstractAction("Set Exercise Test File"){
                public void actionPerformed(ActionEvent e) {
                    JFileChooser chooser = new JFileChooser();
                    if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                        ExecuteThread.setTestPath(chooser.getSelectedFile());
                    }
                }   
            });
    }
    
    @Override
    public JMenuItem getClassMenuItem(final BClass bClass) {
        JMenu menu = new JMenu("Exercise");
        if(!ExecuteThread.hasTestLoaded()){
            JMenuItem item = new JMenuItem("Test");
            item.setEnabled(false);
            menu.add(item);
            return menu;
        }
        try{
            final File excDir = bluej.getCurrentPackage().getDir();
            menu.add(new JMenuItem(new AbstractAction("Test"){
                public void actionPerformed(ActionEvent e) {
                    new ExecuteThread(excDir, bClass.getName()).start();  
                }   
            })); 
        }catch(Exception e){
            System.err.println("Err: " + e);
        }
        return menu;
    }
}