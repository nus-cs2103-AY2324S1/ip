package duke.exceptions;

/**
 * Represents the exception thrown when the user inputs a task with invalid
 * command format.
 * ie the user inputs a event with no /from or /to
 */
public class IncorrectCommandFormatException extends Exception {
    public IncorrectCommandFormatException(String description) {
        super(description == "" ? "OOPS!!! Command format is incorrect!"
                : "OOPS!!! Command format is incorrect!\n    Usage: " + description);
    }
}