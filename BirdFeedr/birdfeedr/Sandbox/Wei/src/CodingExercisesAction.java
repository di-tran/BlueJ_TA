import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

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

public class CodingExercisesAction extends CodingActions{

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
        StateManager.createExerciseListGUI();
	}
}
