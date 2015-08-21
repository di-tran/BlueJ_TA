package JUnitRunner.Exceptions;

/**
 * Thrown if a test is requested to run while a test is already being run by the
 * JUnitRunner
 *
 * @author Nathan
 */
public class IsRunningException extends Exception {

    public IsRunningException(String msg) {
        super(msg);
    }
}
