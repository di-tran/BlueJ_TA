package Extension.BackEnd.Runner;

/**
 * Holds results from junit test and provides a mechanism to wait for results if
 * the test has not been completed yet.
 *
 * @author Nathan
 */
public class ResultHolder {

    /**
     * Whether or not this holder has results yet.
     */
    private boolean hasResults = false;

    /**
     * The results of the junit test from this holders assigned test
     */
    private String results = "";

    /**
     * non public constructor, prevents external creation of ResultHolders 
     */
    ResultHolder(){
    }
    
    /**
     * Updates this holder with the results of its associated junit test and
     * notifies any threads waiting on this object.
     *
     * @param data the results of the junit test.
     */
    synchronized void setData(String data) {
        results = data;
        hasResults = true;
        notifyAll();
    }

    /**
     * Gets the results of the junit test if the test has been completed,
     * returns an empty string otherwise.
     *
     * @return the results of the junit test.
     */
    public synchronized String getData() {
        return results;
    }

    /**
     * Causes the calling thread to wait for this holder to be populated with
     * results, returns instant if this holder already has results.
     */
    public synchronized void waitForResults() {
        if (hasResults) {
            return;
        }
        try {
            wait();
        } catch (Exception e) {
        }
    }
}
