package Actions;

import Exceptions.NoCurrentProjectException;
import Helpers.GitHelper;
import bluej.extensions.BPackage;
import java.awt.event.ActionEvent;
import java.io.IOException;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 * Used to perform the Git Commit command
 * 
 *@author Miguel Roman-Roman
 *@author Josh Gillham
 *@author Thomas Macari
 */
public class GitCommitAction extends GitProjectAction {
    
    /**
     * Used to construct new GitCommitAction object
     * 
     * @param menuName  the name of this object
     * @param msg       the message to be displayed of this object
     * @param aPackage  the current package.
     * 
     * @throws Exceptions.NoCurrentProjectException Thrown when aPackage
     *  is null or if accessing the current project throws a BlueJ exception.
     */
    public GitCommitAction(String menuName, String msg, BPackage aPackage)
            throws NoCurrentProjectException {
        super(menuName, msg, aPackage);
    }
    
    /**
     * Performs the Git Commit command
     * 
     * @param anEvent ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent anEvent) {
        super.actionPerformed( anEvent );
        System.out.println( "projectFolder: " + this.projectFolder);
        try {
        GitHelper.runCommitCommand( this.projectFolder, "Message" );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        catch ( GitAPIException e )
        {
            e.printStackTrace();
        }
    }
    
}
