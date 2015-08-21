import bluej.extensions.*;

import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;

public class MenuBldr extends MenuGenerator {
    
    MenuBldr(BlueJ b){
        StateManager.setUp(b);
        StateManager.setDefaultExercise();
    }
    
    @Override
    public JMenuItem getToolsMenuItem(BPackage aPackage) {
        return new JMenuItem(new AbstractAction("Launch Exercise"){
                public void actionPerformed(ActionEvent e) {
                    new Thread(){
                        public void run(){
                            StateManager.getExercise().launch();
                        }
                    }.start();  
                }
            });
    }
    
    @Override
    public JMenuItem getClassMenuItem(final BClass bClass) {
        if(StateManager.getExercise() == null){
           return null; 
        };
        JMenuItem item = StateManager.getExercise().getMenuItem(bClass);
        if(item == null){
            return null;
        }
        JMenu menu = new JMenu("Exercise");
        menu.add(item);
        return menu;
    }   
}