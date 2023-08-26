package bot.exceptions;

/**
 * Exception for when an index is given that is out of range for the requested operation.
 */
public class InvalidIndexException extends InvalidArgumentException {
    /**
     * Default constructor.
     */
    public InvalidIndexException() {
        super("Sorry, that index doesn't exist. Please key in a valid index.");
    }

    /**
     * Constructor with variable message.
     *
     * @param msg Message to be displayed when getMessage is called.
     */
    public InvalidIndexException(String msg) {
        super(msg);
    }
}
