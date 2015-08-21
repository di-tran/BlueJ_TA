package JUnitRunner;

/**
 * TestListener, listens for to test events from the JUnitRunner
 * 
 * @author Nathan
 */
public interface TestListener {

    /**
     * Called when a test is started.
     */
    public void testStarted();

    /**
     * Called when a test is completed, either by finishing or being canceled.
     *
     * @param results the results of the test.
     */
    public void testEnded(String results);
}
