package duke.exception;

/**
 * This exception is thrown when there is an invalid number indicated after 'mark' and 'unmark'.
 * An invalid number is one that is not in the numbering of the task list.
 */
public class InvalidNumberException extends Exception {
    /**
     * Constructor with an error message.
     */
    public InvalidNumberException() {
        super("Oops! You have indicated an invalid number.");
    }
}
