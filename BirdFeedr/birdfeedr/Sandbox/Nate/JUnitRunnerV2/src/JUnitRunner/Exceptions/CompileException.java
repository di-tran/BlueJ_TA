package JUnitRunner.Exceptions;

/**
 * Thrown if the test code called in JUnitRunner.run doesn't compile.
 *
 * @author Nathan
 */
public class CompileException extends Exception {

    public CompileException(String msg) {
        super(msg);
    }
}
