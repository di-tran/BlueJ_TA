package Extension.BackEnd.Runner;

import bluej.extensions.*;
import java.io.File;

/**
 * Utility methods that generate the various paths to bluej extension locations.
 *
 * @author Nathan
 */
abstract class ExtPathGen {

    /**
     * Generates a class path style string of all the bluej extension directory
     * locations with this extensions jar file name.
     *
     * @param bluej the running bluej program.
     * @param jarName a name or wildcard of this extensions jar file.
     * @return a class path style string of all the bluej extension directory
     * locations with this extensions jar file name.
     */
    static String genExtPaths(BlueJ bluej, String jarName) {
        String cp = getSystemExtDir(bluej) + "/" + jarName + File.pathSeparatorChar
                + getUserExtDir(bluej) + "/" + jarName;
        String projExtDir = getProjExtDir(bluej);
        if (projExtDir != null) {
            cp += File.pathSeparatorChar + projExtDir + "/" + jarName;
        }
        return cp;
    }

    /**
     * Gets the path to the bluej system extension directory.
     *
     * @param bluej the running bluej program.
     * @return the path to the bluej system extension directory.
     */
    static String getSystemExtDir(BlueJ bluej) {
        return bluej.getSystemLibDir().getAbsolutePath() + "/extensions";
    }

    /**
     * Gets the path to the bluej user extension directory.
     *
     * @param bluej the running bluej program.
     * @return the path to the bluej user extension directory.
     */
    static String getUserExtDir(BlueJ bluej) {
        String userHome = bluej.getUserConfigDir().getAbsolutePath();
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            return userHome + "/Library/Preferences/org.bluej/extensions";
        } else {//win or unix (unix untested)
            return userHome + "/extensions";
        }
    }

    /**
     * Gets the path to the currently open bluej project extension directory.
     *
     * @param bluej the running bluej program.
     * @return the path to the currently open bluej extension directory.
     */
    static String getProjExtDir(BlueJ bluej) {
        try {
            return bluej.getCurrentPackage().getDir().getAbsolutePath() + "/extensions";
        } catch (Exception e) {
            return null;
        }
    }
}
