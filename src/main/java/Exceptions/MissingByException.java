package Exceptions;

/**
 * Custom exception class for missing "by" date entries in the Duke application.
 * This exception is thrown when a user attempts to add a deadline task without specifying the "by" date.
 */
public class MissingByException extends DukeException {

    /**
     * Constructs a new MissingByException with a predefined error message.
     * The error message informs the user about the correct format for adding a deadline.
     */
    public MissingByException() {
        super("I DON'T LIKE WHAT YOU'VE GOT! Missing by. ENTER deadline (title) /by (dueDate) to add a deadline");
    }
}
