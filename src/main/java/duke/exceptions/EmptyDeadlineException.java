package duke.exceptions;

/**
 * Custom exception class for empty deadline entries in the Duke application.
 * This exception is thrown when a user attempts to add a deadline task without a title or due date.
 */
public class EmptyDeadlineException extends DukeException {

    /**
     * Constructs a new EmptyDeadlineException with a predefined error message.
     * The error message informs the user about the correct format for adding a deadline.
     */
    public EmptyDeadlineException() {
        super("I DON'T LIKE WHAT YOU'VE GOT! Missing entry. ENTER deadline (title) /by (dueDate) to"
                + " add a deadline");
    }
}
