package RunnerBackEnd;

import JUnitRunner.Exceptions.IsRunningException;
import JUnitRunner.TestListener;
import java.io.File;

/**
 * JUnit Test Assembler and run entry point
 *
 * @author Nathan
 */
public class TestBuilder {

    /**
     * the directory to compile and run the temp files from.
     */
    private final String COMPILE_DIR;

    /**
     * The test Listener to notify on starting and completion of test.
     */
    private TestListener testListener;

    /**
     * The test Handler to start and communicate to the Test runner with.
     */
    private TestHandler testHandler;

    /**
     * Constructor
     *
     * @param compileDir the directory to compile and run the temp files from.
     */
    public TestBuilder(String compileDir) {
        COMPILE_DIR = compileDir;
    }

    /**
     * Splices, compiles, and starts a run of the JUnit test with the users
     * methods.
     *
     * @param userMethods the users method(s) to test
     * @param junitCode the test to perform
     * @throws java.lang.Exception if the test failed to start
     */
    public void exec(String userMethods, String junitCode) throws Exception {
        if (isRunning()) {
            throw new IsRunningException("Unavailable: Previous test still running.");
        }
        File classFile = CodeUtil.compile(COMPILE_DIR, userMethods, junitCode);
        classFile.deleteOnExit();//delete if jvm gets closed by the user mid run
        testHandler = new TestHandler(this, classFile);
        testHandler.start();
    }

    /**
     * Checks if a test is currently being run.
     *
     * @return true if a test is currently being run.
     */
    public boolean isRunning() {
        return testHandler != null && testHandler.isAlive();
    }

    /**
     * Stops the currently running test
     */
    public void stopTest() {
        if (testHandler != null) {
            testHandler.stopTest();
            testHandler = null;
        }
    }

    /**
     * Adds the TestListener to this runners listener queue.
     *
     * @param tl The listener to listen for test events.
     */
    public void setTestListener(TestListener tl) {
        testListener = tl;
    }

    /**
     * Notifies the listeners listening on this runner of a test starting.
     */
    void notifyStarted() {
        testListener.testStarted();
    }

    /*
     * Notifies the listeners listening on this runner of a test completion.
     */
    void notifyCompletion(String results) {
        testListener.testEnded(results);
    }

}
