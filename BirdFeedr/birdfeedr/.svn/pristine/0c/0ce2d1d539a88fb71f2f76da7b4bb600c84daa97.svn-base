package Actions;

import Exceptions.NoCurrentProjectException;
import Helpers.GitHelper;
import bluej.extensions.BPackage;
import java.awt.event.ActionEvent;
import java.io.IOException;
import org.eclipse.jgit.api.errors.GitAPIException;

public class GitPushAction extends GitProjectAction {
    
    /**
     * Used to construct new GitPushAction object
     * 
     * @param menuName  the name of this object
     * @param msg       the message to be displayed of this object
     * @param aPackage  the current package.
     * 
     * @throws Exceptions.NoCurrentProjectException Thrown when aPackage
     *  is null or if accessing the current project throws a BlueJ exception.
     */
    public GitPushAction(String menuName, String msg, BPackage aPackage)
            throws NoCurrentProjectException {
        super(menuName, msg, aPackage);
    }
    
    /**
     * Performs the Git Push command
     * 
     * @param anEvent ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent anEvent) {
        super.actionPerformed( anEvent );
        try {
        GitHelper.runPushCommand( this.projectFolder,
                "null", "username", "password" );
        }
        catch ( GitAPIException e )
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
