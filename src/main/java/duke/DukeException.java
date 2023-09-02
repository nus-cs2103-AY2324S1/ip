package duke;

/**
 * Custom exception class for Duke, a task management application.
 * This exception is used to handle errors and exceptional situations specific to Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified detail message.
     * @param message the detail message
     */
    public DukeException(String message) {
        super(message);
    }
}
