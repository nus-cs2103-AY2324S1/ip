package duke.exceptions;

/**
 * Represents the exception thrown when the user inputs a task with invalid
 * command format.
 * ie the user inputs a event with no /from or /to
 */
public class IncorrectCommandFormatException extends Exception {
    /**
     * Creates an IncorrectCommandFormatException object.
     * 
     * @param description The description of the exception.
     */
    public IncorrectCommandFormatException(String description) {
        super(description == "" ? "OOPS!!! Command format is incorrect!"
                : "OOPS!!! Command format is incorrect!\n    Usage: " + description);
    }
}