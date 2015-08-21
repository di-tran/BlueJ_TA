import bluej.extensions.*;
import java.io.*;

public abstract class FileUtil {
    
    public static String getName(String code){
        int i = code.indexOf("class") + 5;
        if (i == 4) {//-1 + 5 = 4
            return null;
        }
        int k = code.indexOf('{');
        return code.substring(i, k).trim();
    }
    
    public static boolean save(File path, String contents){
        String[] lines = contents.split(System.lineSeparator());
        try{
            PrintWriter out = new PrintWriter(path);
            for (String line : lines) {
                out.println(line);
            }
            out.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * 
     * @see http://examples.javacodegeeks.com/core-java/io/file/4-ways-to-copy-file-in-java/
     */
    public static void copy(File source, File dest){
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
            input.close();
            output.close();
        }catch(Exception e){
            System.err.println("Error: " + e);
        }
    }
    
    /**
     * 
     * @see http://www.rgagnon.com/javadetails/java-0483.html
     */
    public static boolean deleteDir(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDir(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }
    
    public static void closeProj(BlueJ bluej, String name){
        for(BProject proj : bluej.getOpenProjects()){
            try{
                if(proj.getName().equals(name)){
                    proj.close();
                }   
            }catch(Exception e){
            }    
        }
    }
}