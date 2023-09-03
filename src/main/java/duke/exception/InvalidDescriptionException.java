package duke.exception;

/**
 * This exception is thrown when there is an invalid task description or when the keyword
 * is not indicated for 'find'.
 * An invalid task description is one that is empty.
 */
public class InvalidDescriptionException extends Exception {
    /**
     * Constructor with an error message.
     */
    public InvalidDescriptionException(String task) {
        super("☹ OOPS!!! "
                + (task.equals("find")
                ?  "You didn't indicate the keyword."
                : "The description of a " + task + " cannot be empty."));
    }
}
