package JUnitRunner;

import RunnerBackEnd.TestBuilder;

/**
 * Runs JUnit test with users methods spliced in
 *
 * @author Nathan
 */
public class JUnitRunner {

    private final TestBuilder builder;

    /**
     * Constructor, uses the local working directory to compile temporary files
     * in.
     */
    public JUnitRunner() {
        this("");//use local working dir
    }

    /**
     * Constructor
     *
     * @param compileDir the directory to compile and run the temp files from.
     */
    public JUnitRunner(String compileDir) {
        builder = new TestBuilder(compileDir);
    }

    /**
     * Splices, compiles, and starts a run of the JUnit test with the users
     * methods.
     *
     * @param userMethods the users method(s) to test
     * @param junitCode the test to perform
     * @throws java.lang.Exception if the test failed to start
     */
    public void run(String userMethods, String junitCode) throws Exception {
        builder.exec(userMethods, junitCode);
    }

    /**
     * Checks if a test is currently being run.
     *
     * @return true if a test is currently being run.
     */
    public boolean isRunning() {
        return builder.isRunning();
    }

    /**
     * Stops the currently running test
     */
    public void stopTest() {
        builder.stopTest();
    }

    /**
     * Adds the TestListener to this runners listener queue.
     *
     * @param tl The listener to listen for test events.
     */
    public void setTestListener(TestListener tl) {
        builder.setTestListener(tl);
    }
}
