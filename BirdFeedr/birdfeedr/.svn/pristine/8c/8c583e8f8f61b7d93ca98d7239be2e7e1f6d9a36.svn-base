import bluej.extensions.*;
import bluej.extensions.event.*;
import java.net.URL;

/*
 * This is the starting point of a BlueJ Extension
 */
public class SimpleExtension extends Extension implements PackageListener
{
    /*
     * When this method is called, the extension may start its work.
     */
    public void startup(BlueJ bluej)
    {
        // Listen for BlueJ events at the "package" level
        bluej.addPackageListener(this);
    }

    /*
     * A package has been opened. Print the name of the project it is part of.
     * System.out is redirected to the BlueJ debug log file.
     * The location of this file is given in the Help/About BlueJ dialog box.
     */
    public void packageOpened(PackageEvent ev)
    {
        try
        {
            System.out.println("Project " + ev.getPackage().getProject().getName() 
                               + " opened.");
        }
        catch (ExtensionException e)
        {
            System.out.println("Project closed by BlueJ");
        }
    } 
               
    /*
     * A package is closing.
     */
    public void packageClosing(PackageEvent ev)
    {
    } 
               
    /*
     * This method must decide if this Extension is compatible with the                
     * current release of the BlueJ Extensions API
     */
    public boolean isCompatible()
    { 
        return true; 
    }

    /*
     * Returns the version number of this extension
     */
    public String getVersion ()
    { 
        return ("2004.09"); 
    }

    /*
     * Returns the user-visible name of this extension
     */
    public String getName ()
    { 
        return ("Simple Extension"); 
    }

    public void terminate()
    {
        System.out.println ("Simple extension terminates");
    }
               
    public String getDescription ()
    {
        return ("A simple extension");
    }

    /*
     * Returns a URL where you can find info on this extension.
     * The real problem is making sure that the link will still be alive 
     * in three years...
     */
    public URL getURL ()
    {
        try
        {
            return new URL("http://www.bluej.org/doc/writingextensions.html");
        }
        catch ( Exception e )
        {
            // The link is either dead or otherwise unreachable
            System.out.println ("Simple extension: getURL: Exception="+e.getMessage());
            return null;
        }
    }
}