package exceptions;

/**
 * Exception for incorrect inputs when creating a Task object.
 */
public class InvalidTaskException extends BotException {
    /**
     * Constructor with variable message.
     *
     * @param msg Message to be displayed when getMessage is called.
     */
    public InvalidTaskException(String msg) {
        super(msg);
    }
}
