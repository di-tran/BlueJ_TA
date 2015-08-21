import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.runner.Result;

/**
 * Static JUnit Runner Demo
 *
 * @author Nathan
 */
public class StaticJUnitRunnerDemo {

    public static void main(String[] args) throws IOException {

        String compileDir = new File(".").getCanonicalPath() + "/";
        String junitCode = load(compileDir + "JUnitDemoCode.txt");
        String userCode = load(compileDir + "UserDemoCode.txt");

        Result results;
        try {
            results = StaticJUnitRunner.run(compileDir, userCode, junitCode);
            System.out.println("Tests: " + results.getRunCount() + "\n"
                    + "Fails: " + results.getFailureCount());
            for (Object fail : results.getFailures()) {
                System.out.println("    " + fail);
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    private static String load(String file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text = "";
            String newLine = System.lineSeparator();
            String line;
            while ((line = reader.readLine()) != null) {
                text += line + newLine;
            }
            return text;
        } catch (Exception e) {
            System.err.println("Failed to open file: <" + file + ">, " + e);
        }
        return null;
    }
}
