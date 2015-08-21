import Actions.*;
import Exceptions.NoCurrentProjectException;
import bluej.extensions.BClass;
import bluej.extensions.BObject;
import bluej.extensions.BPackage;
import bluej.extensions.MenuGenerator;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * This class extends MenuGenerator from the BlueJ extension API and is used
 * to create Menu items for the BlueJ IDE.
 * 
 *@author Miguel Roman-Roman
 *@author Josh Gillham
 *@author Thomas Macari
 */
public class MenuBuilder extends MenuGenerator {
    /**
     * Adds Git menu items to the BlueJ tools menu.
     * 
     * @param aPackage  the package
     * @return  the new menu item to be added to BlueJ tools menu
     */
    @Override
    public JMenuItem getToolsMenuItem(BPackage aPackage) {
        JMenu jm = new JMenu("Git");
        // The following Git actions require an open BlueJ project.
        try {
            jm.add(new JMenuItem(
                    new GitCommitAction("Git Commit",
                            "Files succesfully committed",aPackage )
            ));
            jm.add(new JMenuItem(
                    new GitInitAction("Git Init", "Git initialized",aPackage)
            ));
            jm.add(new JMenuItem(
                    new GitPullAction("Git Pull", "Git pulled",aPackage)
            ));
        }
        // Thrown when a BlueJ project is not open.
        //  The exception is handled because the menu items requiring an
        //  open project do not get added to the menu.
        catch (NullPointerException ex ) {}
        catch (NoCurrentProjectException ex ) {}
        return jm;
    }
}