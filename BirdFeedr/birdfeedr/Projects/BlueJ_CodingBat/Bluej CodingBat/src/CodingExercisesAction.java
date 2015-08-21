import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * The action that is performed when the user clicks the Exercises "button" in
 * BlueJ.
 * 
 * @author Miguel Roman-Roman
 * @author Wei Huang
 * @author Di Tran
 * @author Josh Gillham
 * @author Nathan Witt
 * @author Thomas Macari
 */

public class CodingExercisesAction extends AbstractAction{

    //unused
    private static final long serialVersionUID = 1L;

        /**
         * Constructs a CodingAction used for when the user clicks the 
         * exercises "button"
         * 
         * @param menuName
         */
    public CodingExercisesAction(String menuName) {
        super(menuName);
    }

    /**
         * Starts the GUI for this extension
         * 
         * @param event ActionEvent passed
         */
    @Override
    public void actionPerformed(ActionEvent event) {
        /*if(StateManager.exercisesLocation == null) {
            StateManager.showErrorMessage("Exercise Location not Set. \n" +
                                "Go to BlueJ Preferences->Extension to set path");
        }
        else {*/
            StateManager.createExerciseListGUI();
        //}
    }
}
