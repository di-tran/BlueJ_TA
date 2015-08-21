package Actions;

import Exceptions.GitExtensionException;
import Exceptions.NoCurrentProjectException;
import Helpers.BlueJHelper;
import bluej.extensions.BPackage;
import bluej.extensions.ExtensionException;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 * A class that acts as the default for actions to be performed in BlueJ
 * 
 *@author Miguel Roman-Roman
 *@author Josh Gillham
 *@author Thomas Macari
 */
public class GitProjectAction extends AbstractAction {
    
    public String msgHeader;
    protected final File projectFolder;
        
    /**
     * Used to construct new SimpleAction object
     * 
     * @param menuName  the name of this object
     * @param msg       the message to be displayed of this object
     * @param aPackage  the current package.
     * 
     * @throws Exceptions.NoCurrentProjectException Thrown when aPackage
     *  is null or if accessing the current project throws a BlueJ exception.
     */
    public GitProjectAction(String menuName, String msg, BPackage aPackage)
            throws NoCurrentProjectException {
        try {
            projectFolder = BlueJHelper.getProjectDir( aPackage.getProject() );
        } catch (ExtensionException ex) {
            throw new NoCurrentProjectException( ex );
        }
        
        putValue(AbstractAction.NAME, menuName);
        msgHeader = msg;
    }
    
    /**
     * Performs an action
     * 
     * @param anEvent ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent anEvent) {
         showCurrentStatus(msgHeader);
    }
    
    /**
     * Opens a JOptionPane displaying a message.
     * 
     * @param header the message to be displayed
     */
    private void showCurrentStatus(String header) {
        JOptionPane.showMessageDialog(null, header);
    }   
}
