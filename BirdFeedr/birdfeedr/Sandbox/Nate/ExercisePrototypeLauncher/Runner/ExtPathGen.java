package Runner;

import bluej.extensions.*;
import java.io.File;

public abstract class ExtPathGen {
   
    public static String genExtPaths(BlueJ bluej, String jarName){
        String cp = getSystemExtDir(bluej) + "/" + jarName + File.pathSeparatorChar
                  + getUserExtDir(bluej) + "/" + jarName;
        String projExtDir = getProjExtDir(bluej);
        if(projExtDir != null){
            cp += File.pathSeparatorChar + projExtDir + "/" + jarName;
        }
        return cp;
    }
    
    public static String getSystemExtDir(BlueJ bluej){
        return bluej.getSystemLibDir().getAbsolutePath() + "/extensions"; 
    }
    
    public static String getUserExtDir(BlueJ bluej){
        String userHome = bluej.getUserConfigDir().getAbsolutePath();
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("mac")){
            return userHome + "/Library/Preferences/org.bluej/extensions";
        }else{//win or unix (unix untested)
            return userHome + "/extensions";
        } 
    }
    
    public static String getProjExtDir(BlueJ bluej){
         try{
            return bluej.getCurrentPackage().getDir().getAbsolutePath() + "/extensions";
        }catch(Exception e){
            return null;
        }
    }
}
