import bluej.extensions.*;
import bluej.extensions.event.*;
import java.net.URL;
/**
 * GitExtension is the main starting point for the Git Extension.
 * It extends Extension from the BlueJ API and overrides necessary methods.
 *
 *
 * This is the starting point of a BlueJ Extension
 *
 *
 *@author Miguel Roman-Roman
 *@author Josh Gillham
 *@author Thomas Macari
 */
public class GitExtension extends Extension implements PackageListener {
    
    /**
     * When this method is called, the extension may start its work.
     * 
     * @param bluej instance of bluej
     */
    @Override
    public void startup (BlueJ bluej) {
        // Register a generator for menu items
        bluej.setMenuGenerator(new MenuBuilder());

        // Register a "preferences" panel generator
        Preferences myPreferences = new Preferences(bluej);
        bluej.setPreferenceGenerator(myPreferences);

        // Listen for BlueJ events at the "package" level
        bluej.addPackageListener(this);
    }
    
    /**
     * A package has been opened. Print the name of the project it is part of.
     * System.out is redirected to the BlueJ debug log file.
     * The location of this file is given in the Help/About BlueJ dialog box.
     *
     * @param ev PackageEvent
     */
    @Override
    public void packageOpened ( PackageEvent ev ) {
        try {
            System.out.println ("Project " + ev.getPackage().getProject().getName() + " opened.");
        } catch (ExtensionException e) {
            System.out.println("Project closed by BlueJ");
        }
    }  
  
    /**
     * A package is closing.
     * 
     * @param ev PackageEvent
     */
    @Override
    public void packageClosing ( PackageEvent ev ) {
    }  
    
    /**
     * This method must decide if this Extension is compatible with the 
     * current release of the BlueJ Extensions API
     * 
     * @return  true if this extension is compatible, false otherwise.
     */
    @Override
    public boolean isCompatible () { 
        return true; 
    }

    /**
     * Returns the version number of this extension
     * 
     * @return  the current version of this extension
     */
    @Override
    public String  getVersion () { 
        return ("2015.02");  
    }

    /**
     * Returns the user-visible name of this extension
     * 
     * @return  the name of this extension
     */
    @Override
    public String  getName () { 
        return ("Git Extension");  
    }

    /**
     * Called when this extension is terminated
     */
    @Override
    public void terminate() {
        System.out.println ("Git Extension Terminates");
    }
    
    /**
     * Provides the description of this extension.
     * 
     * @return  this program's description 
     */
    @Override
    public String getDescription () {
        return ("An extension that enables users to connect to Git repositories");
    }

    /**
     * Returns a URL where you can find info on this extension.
     * 
     * @return  the url of the website for this extension.
     */
    @Override
    public URL getURL () {
        try {
            return
                    new URL("http://gouda.msudenver.edu/redmine/projects/project-git");
        } catch ( Exception eee ) {
            // The link is either dead or otherwise unreachable
            System.out.println ("Simple extension: getURL: Exception="+eee.getMessage());
            return null;
        }
    }
}