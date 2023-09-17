package seedu.dookie;

/**
 * Encapsulates the exception where the task identifier command by the user is not specified.
 */
public class TaskTypeException extends DookieException {
    /**
     * Creates a TaskTypeException instance.
     */
    public TaskTypeException() {
        super("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
