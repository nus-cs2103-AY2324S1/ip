package duke.task;

/**
 * Represents an error that occurred during the initialisation of a Deadline object.
 */
public class TaskException extends Exception {

    public TaskException(String format) {
        super("Oops! Invalid input for your task.\nValid Format: " + format);
    }
}
