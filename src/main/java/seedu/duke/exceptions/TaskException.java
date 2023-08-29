package seedu.duke.exceptions;

/**
 * TaskException
 */
public class TaskException extends Exception {
    public TaskException(String message) {
        super("TaskException: " + message + "\n");
    }
}
