package RunnerBackEnd;

import JUnitRunner.Exceptions.CompileException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Static Code Utility methods, like compile and save
 *
 * @author Nathan
 */
public abstract class CodeUtil {

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

    /**
     * Splices the two codes together placing the users method(s) into the JUnit
     * code.
     *
     * @param userCode the methods to splice into the JUnit code.
     * @param junitCode the JUnit code.
     * @return the JUnit code with the users methods spliced in.
     */
    public static String splice(String userCode, String junitCode) {
        int i = junitCode.indexOf('{') + 1;
        String code = junitCode.substring(0, i) + System.lineSeparator()
                + userCode
                + junitCode.substring(i);
        return code;
    }

    /**
     * Saves the given contents into the file given by filePath.
     *
     * @param filePath the file to save.
     * @param contents the contents of the file to save.
     * @throws FileNotFoundException if the file could not be saved.
     */
    public static void saveFile(File filePath, String contents) throws FileNotFoundException {
        String[] lines = contents.split(System.lineSeparator());
        PrintWriter out = new PrintWriter(filePath);
        for (String line : lines) {
            out.println(line);
        }
        out.close();
    }

    /**
     * Loads a class into memory from the given class file.
     *
     * @param classFile the class file to load.
     * @return the class loaded into memory.
     * @throws Exception if the class load failed.
     */
    public static Class loadClass(File classFile) throws Exception {
        URL url = classFile.getParentFile().toURI().toURL();
        URLClassLoader classLdr = URLClassLoader.newInstance(new URL[]{url});
        String className = classFile.getName().split("\\.")[0];
        return Class.forName(className, true, classLdr);
    }

    /**
     * Attempts to compile the given java file, will return the compiled class
     * file if successful or throw a CompileException otherwise.
     *
     * @see CompileException
     *
     * @param filePath the java file to compile.
     * @return the compiled class file.
     * @throws CompileException if the compile failed. note) This exception
     * should contain the errors that caused the compile to fail.
     */
    public static File compile(File filePath) throws CompileException {
        String filePathStr = filePath.getAbsolutePath();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();//returns null if using jre and not jdk
        ByteArrayOutputStream errOut = new ByteArrayOutputStream();//used to grab the error
        int state = compiler.run(System.in, System.out, errOut, filePathStr);
        if (state == 0) {
            return new File(filePathStr.split("\\.")[0] + ".class");
        }
        String msg = new String(errOut.toByteArray(), Charset.defaultCharset());
        throw new CompileException(msg);
    }

    /**
     * Attempts to splice and compile the two given codes and returns the
     * resulting class file, throws an Exception if an compilation or file not
     * found error occurred.
     *
     * @param compileDir the directory to compile and run the temp files from.
     * @param userMethods the users method(s) to test
     * @param junitCode the test to perform
     * @return the compiled JUnit .class file of the spliced codes.
     * @throws Exception if a compilation or file not found error occurred.
     */
    public static File compile(String compileDir, String userMethods, String junitCode) throws Exception {
        File codeFile = new File(compileDir + CodeUtil.getClassName(junitCode) + ".java");
        try {
            String code = CodeUtil.splice(userMethods, junitCode);
            CodeUtil.saveFile(codeFile, code);
            File classFile = CodeUtil.compile(codeFile);
            codeFile.delete();
            return classFile;
        } catch (Exception e) {
            codeFile.delete();
            throw e;
        }
    }
}
