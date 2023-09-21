package bot.exceptions;

/**
 * Exception for when duplicate tasks are encountered and are not supposed to exist.
 */
public class DuplicateTaskException extends InvalidTaskException {
    /**
     * Constructor with default message.
     */
    public DuplicateTaskException() {
        super("Task already exists!");
    }

    /**
     * Constructor with variable message.
     *
     * @param msg Message to be displayed when getMessage is called.
     */
    public DuplicateTaskException(String msg) {
        super(msg);
    }
}
