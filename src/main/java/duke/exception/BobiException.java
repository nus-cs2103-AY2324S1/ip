package duke.exception;

/**
 * BobiException is the base class for all exceptions thrown while using Bobi.
 * A BobiException object encapsulates the error message
 * which will be shown when this exception is thrown.
 *
 * @author ruo-x
 */
public class BobiException extends Exception{
    /**
     * Constructor of a BobiException object.
     *
     * @param message Error Message to be shown to the user.
     */
    public BobiException(String message) {
        super(message);
    }
}
