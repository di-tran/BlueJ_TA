package Extension;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import bluej.extensions.BPackage;
import bluej.extensions.BClass;
import bluej.extensions.MenuGenerator;

import Extension.BackEnd.*;

/**
 * Creates Menu items for this BlueJ extension
 * 
 * @author Miguel Roman-Roman
 * @author Wei Huang
 * @author Di Tran
 * @author Josh Gillham
 * @author Nathan Witt
 * @author Thomas Macari
 */
public class MenuBuilder extends MenuGenerator {

    /**
     * Adds extension menu items to the BlueJ tools menu
     * 
     * @param bPackage  BlueJ package
     * @return     the menu item to be added to BlueJ tools menu
     */
    @Override
    public JMenuItem getToolsMenuItem(BPackage bPackage){
            return new JMenuItem(new AbstractAction("Coding Practice"){
                @Override
                public void actionPerformed(ActionEvent event) {
                    StateManager.createExerciseListGUI();
                }
            });
    }
    
    @Override
    public JMenuItem getClassMenuItem(BClass bClass) {
        if(StateManager.getCurrentExercise() == null || 
            !StateManager.getCurrentExercise().getJavaName().equals(bClass.getName())){
            return null;
        }
        
        JMenu jm = new JMenu("Coding Practice");
        jm.add(new JMenuItem(new AbstractAction("Exercise Description"){
            @Override
            public void actionPerformed(ActionEvent event) {
                if(StateManager.getCurrentExercise() == null) {
                    StateManager.showErrorMessage("No Exercise Selected\n" +
                    "Go to Tools->CodingBat to pick exercise");
                }else {
                    StateManager.createDescriptionGUI();
                }
            }
        }));
        jm.add(new JMenuItem(new AbstractAction("Execute"){
            @Override
            public void actionPerformed(ActionEvent event) {
                if(StateManager.getCurrentExercise() == null) {
                    StateManager.showErrorMessage("No Exercise Selected\n" +
                    "Go to Tools->CodingBat to pick exercise");
                }else{
                    StateManager.createTestResultsGUI();
                }
            }
        }));
        return jm;
    }
}
