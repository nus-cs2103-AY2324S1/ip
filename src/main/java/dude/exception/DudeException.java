package dude.exception;

/**
 * Exception specific to Dude.
 */
public class DudeException extends Exception {
    /**
     * Constructs new Dude exception with message
     *
     * @param message Human-readable error message.
     */
    public DudeException(String message) {
        super(message);
    }

    /**
     * Constructs new Dude exception with default message
     */
    public DudeException() {
        super("An error occurred. :(");
    }

}
