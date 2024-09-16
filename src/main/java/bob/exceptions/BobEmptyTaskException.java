package bob.exceptions;

/**
 * Represents an exception when description of a Task is empty.
 */
public class BobEmptyTaskException extends BobException {

    /**
     * Constructor for this exception.
     *
     * @param message the task type.
     */
    public BobEmptyTaskException(String message) {
        super("Eyyyy, the description of a " + message + " cannot be empty!");
    }
}
