import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * A class that acts  as the default action to be performed in BlueJ
 * Classes that implement CodingActions are "buttons" that will appear
 * within the BlueJ tool bar.
 * 
 * @author Miguel Roman-Roman
 * @author Wei Huang
 * @author Di Tran
 * @author Josh Gillham
 * @author Nathan Witt
 * @author Thomas Macari
 */

public abstract class CodingActions extends AbstractAction{

	//unused
	private static final long serialVersionUID = 1L;
	
        /**
         * Constructs a Coding action with given name
         * 
         * @param menuName  The name to be put in the BlueJ toolbar 
         */
	public CodingActions(String menuName){
		putValue(AbstractAction.NAME, menuName);
	}

        /**
         * The action that this object performs.
         * Classes that implement CodingActions must override this method
         * 
         * @param event ActionEvent passed
         */
	@Override
	public abstract void actionPerformed(ActionEvent event);

}
