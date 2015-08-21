import bluej.extensions.BlueJ;
import bluej.extensions.Extension;
import bluej.extensions.event.CompileEvent;
import bluej.extensions.event.CompileListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Nathan
 */
public class ChirpDirExt extends Extension implements CompileListener {

    private File dir, udir;

    /*
     * When this method is called, the extension may start its work.
     */
    public void startup(BlueJ bluej) {
        bluej.addCompileListener(this);

        dir = bluej.getSystemLibDir();
        udir = bluej.getUserConfigDir();
    }

    @Override
    public void compileStarted(CompileEvent ce) {
    }

    @Override
    public void compileError(CompileEvent ce) {
    }

    @Override
    public void compileWarning(CompileEvent ce) {
    }

    @Override
    public void compileSucceeded(CompileEvent ce) {
        playSound(dir.getAbsolutePath() + "/extensions/Chirp.wav");
    }

    private static void playSound(String file) {
        try {
            InputStream in = new FileInputStream(file);
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    @Override
    public void compileFailed(CompileEvent ce) {
    }

    /*
     * This method must decide if this Extension is compatible with the 
     * current release of the BlueJ Extensions API
     */
    public boolean isCompatible() {
        return true;
    }

    /*
     * Returns the version number of this extension
     */
    public String getVersion() {
        return ("2015.01");
    }

    /*
     * Returns the user-visible name of this extension
     */
    public String getName() {
        return "Chirp";
    }

    public void terminate() {
        System.out.println(getName() + " terminates");
    }

    public String getDescription() {
        return getName();
    }

    /*
     * Returns a URL where you can find info on this extension.
     * The real problem is making sure that the link will still be alive in three years...
     */
    public URL getURL() {
        return null;
    }
}
