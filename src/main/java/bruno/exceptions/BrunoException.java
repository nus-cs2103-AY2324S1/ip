package bruno.exceptions;

/**
 * The BrunoException is the superclass for all exception handling classes.
 */
public class BrunoException extends Exception {

    /**
     * Creates a new instance of BrunoException/
     * @param message The message conveying the exception.
     */
    public BrunoException(String message) {
        super(message);
        assert !message.isBlank() : "Exception message is empty!";
    }
}
