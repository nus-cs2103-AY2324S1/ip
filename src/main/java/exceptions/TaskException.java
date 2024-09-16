package exceptions;

/**
 * The exception class for Tasks.
 *
 * @author Gallen Ong
 */
public class TaskException extends RuntimeException {
    /**
     * Initialises a task exception for invalid task numbers.
     */
    public TaskException() {
        super("Invalid task provided.");
    }
}
