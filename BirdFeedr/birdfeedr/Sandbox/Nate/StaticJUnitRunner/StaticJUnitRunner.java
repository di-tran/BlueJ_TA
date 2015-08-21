import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * JUnitRunner, runs varying JUnit test with varying user methods.
 *
 * @author Nathan
 */
public abstract class StaticJUnitRunner {

    /**
     * Executes the given JUnit test with the given users code and returns the
     * results.
     *
     * @param compileDir the directory to save, compile, and delete the
     * temporary files within.
     * @param userMethods the users method(s) to test
     * @param junitCode the test to perform
     * @return the results of the JUnit test run with the users methods.
     * @throws Exception if an error occurred in the process.
     */
    public static Result run(String compileDir, String userMethods, String junitCode) throws Exception {
        File codePath = null;
        File classPath = null;
        try {
            codePath = new File(compileDir + getClassName(junitCode) + ".java");
            String code = splice(userMethods, junitCode);

            saveFile(codePath, code);
            classPath = compile(codePath);

            codePath.delete();

            Class testClass = loadClass(classPath);
            Result result = new JUnitCore().run(testClass);

            classPath.delete();

            return result;
        } catch (Exception e) {
            if (codePath != null) {
                codePath.delete();
            }
            if (classPath != null) {
                classPath.delete();
            }
            throw e;
        }
    }

    /**
     * Extracts the first class name from the given class code.
     *
     * @param code the code to scan.
     * @return the name of the class within the code if any, otherwise returns
     * null.
     */
    private static String getClassName(String code) {
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
    private static String splice(String userCode, String junitCode) {
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
    private static void saveFile(File filePath, String contents) throws FileNotFoundException {
        String[] lines = contents.split(System.lineSeparator());
        PrintWriter out = new PrintWriter(filePath);
        for (String line : lines) {
            out.println(line);
        }
        out.close();
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
    private static File compile(File filePath) throws CompileException {
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
     * Loads a class into memory from the given class file.
     *
     * @param filePath the class file to load.
     * @return the class loaded into memory.
     * @throws Exception if the class load failed.
     */
    private static Class loadClass(File classFile) throws Exception {
        URL url = classFile.getParentFile().toURI().toURL();
        URLClassLoader classLdr = URLClassLoader.newInstance(new URL[]{url});
        String className = classFile.getName().split("\\.")[0];
        return Class.forName(className, true, classLdr);
    }

    /**
     * A exception used to indicate a compilation failure of a given code.
     */
    private static class CompileException extends Exception {

        private CompileException(String msg) {
            super(msg);
        }
    }
}
