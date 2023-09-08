package dude.exception;

/**
 * Exception specific to dude.Dude (Duke)
 */
public class DudeException extends Exception {
    /**
     * Constructor for dude.Dude exception with message
     *
     * @param message Human-readable error message.
     */
    public DudeException(String message) {
        super(message);
    }

    /**
     * Constructor for dude.Dude exception with default message
     */
    public DudeException() {
        super("An error occurred. :(");
    }

}
