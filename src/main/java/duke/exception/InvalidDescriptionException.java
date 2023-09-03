package duke.exception;

/**
 * This exception is thrown when there is an invalid task description.
 * An invalid task description is one that is empty.
 */
public class InvalidDescriptionException extends Exception {
    /**
     * Constructor with an error message.
     */
    public InvalidDescriptionException(String task) {
        super("☹ OOPS!!! The description of a " + task + " cannot be empty.");
    }
}
