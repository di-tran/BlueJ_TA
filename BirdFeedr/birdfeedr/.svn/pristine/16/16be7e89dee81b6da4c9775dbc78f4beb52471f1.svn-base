package Actions;

import Exceptions.NoCurrentProjectException;
import Helpers.GitHelper;
import bluej.extensions.BPackage;
import java.awt.event.ActionEvent;

/**
 * Used to perform the Git Init command
 * 
 *@author Miguel Roman-Roman
 *@author Josh Gillham
 *@author Thomas Macari
 */
public class GitInitAction extends GitProjectAction {
       
    /**
     * Used to construct new GitInitAction object
     * 
     * @param menuName  the name of this object
     * @param msg       the message to be displayed of this object
     * @param aPackage  the current package.
     * 
     * @throws Exceptions.NoCurrentProjectException Thrown when aPackage
     *  is null or if accessing the current project throws a BlueJ exception.
     */
    public GitInitAction(String menuName, String msg, BPackage aPackage)
            throws NoCurrentProjectException {
        super( menuName, msg, aPackage );
    }
        
    /**
     * Performs the Git Init command
     * 
     * @param anEvent ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent anEvent) {
        super.actionPerformed( anEvent );
        try {
        GitHelper.runInitCommand( this.projectFolder );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
    
}