/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import bluej.extensions.BPackage;
import bluej.extensions.BProject;
import bluej.extensions.BlueJ;
import bluej.extensions.ExtensionException;
import java.io.File;

/**
 * This class provides a set of methods that simplify interaction with BlueJ.
 *  Currently, the methods get the project path.
 * 
 * @author Josh Gillam
 * @author Miguel
 */
public class BlueJHelper {
    
    /**
     * Returns the directory of the current BlueJ project using an instance
     *  of BlueJ.
     * 
     * @param instance The BlueJ instance.
     * 
     * @return The project directory.
     * 
     * @throws ExtensionException This will most likely occur when
     *   there is no current project.
     */
    public static File getProjectDir(BlueJ instance)
            throws ExtensionException
    {
        BPackage bPackage = instance.getCurrentPackage();
        return getProjectDir(bPackage);
    } 
    
    /**
     * Returns the directory of the current BlueJ project using the
     *   current BlueJ package.
     * 
     * @param instance The current package.
     * 
     * @return The project directory.
     * 
     * @throws ExtensionException This will most likely occur when
     *   there is no current project.
     */
    public static File getProjectDir(BPackage instance)
            throws ExtensionException
    {
        return getProjectDir(instance.getProject());
    }
    
    /**
     * Returns the directory of the current BlueJ project using the current
     *  project.
     * 
     * @param instance The current project.
     * 
     * @return The project directory.
     * 
     * @throws ExtensionException This will most likely occur when
     *   there is no current project.
     */
    public static File getProjectDir(BProject instance)
            throws ExtensionException
    {
        return instance.getDir();
    }
}
