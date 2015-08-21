

public abstract class JavaUtil {
    
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
