import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public abstract class FileUtil {
    
    /**
     * Loads the file contents into a text string
     *
     * @param file the file to load
     * @return a string containing the files contents
     */
    public static String load(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text = "";
            String newLine = System.lineSeparator();
            String line;
            while ((line = reader.readLine()) != null) {
                text += line + newLine;
            }
            reader.close();
            return text;
        } catch (Exception e) {
            System.err.println("Failed to open file: <" + file + ">, " + e);
        }
        return null;
    }
    
    /**
     * Extracts the first class name from the given class code.
     *
     * @param code the code to scan.
     * @return the name of the class within the code if any, otherwise returns
     * null.
     */
    public static String getClassName(String code) {
        int i = code.indexOf("class") + 5;
        if (i == 4) {
            return null;
        }
        int k = code.indexOf('{');
        return code.substring(i, k).trim();
    }
}
