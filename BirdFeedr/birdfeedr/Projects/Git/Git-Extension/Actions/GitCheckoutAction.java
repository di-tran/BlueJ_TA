package Actions;

import Exceptions.NoCurrentProjectException;
import bluej.extensions.BPackage;
import java.awt.event.ActionEvent;

/**
 * Used to perform the Git Checkout command
 * 
 *@author Miguel Roman-Roman
 *@author Josh Gillham
 *@author Thomas Macari
 */
public class GitCheckoutAction extends GitProjectAction {

    /**
     * Used to create a new GitCheckoutAction object
     * 
     * @param menuName  the name of this command
     * @param msg       the message to be displayed when this command runs
     * @param aPackage  the current package.
     * 
     * @throws Exceptions.NoCurrentProjectException Thrown when aPackage
     *  is null or if accessing the current project throws a BlueJ exception.
     */
    public GitCheckoutAction(String menuName, String msg, BPackage aPackage)
            throws NoCurrentProjectException {
        super(menuName, msg, aPackage);
    }    
    
    /**
     * Performs the checkout command
     * 
     * @param anEvent ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent anEvent) {
        super.actionPerformed(anEvent);
        
        
    }
}
