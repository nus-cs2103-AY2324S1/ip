package duke.exceptions;

/**
 * Custom exception class for invalid input entries in the Duke application.
 * This exception is thrown when a user provides an input that doesn't match any valid command formats.
 */
public class InvalidInputException extends DukeException {

    /**
     * Constructs a new InvalidInputException with a predefined error message.
     * The error message informs the user about the correct format to start various types of tasks.
     */
    public InvalidInputException() {
        super("I DON'T LIKE WHAT YOU'VE GOT! START WITH todo (title) or "
                + "deadline (title) /by (dueDate) or event (title) /from (from) /to (to)");
    }
}
