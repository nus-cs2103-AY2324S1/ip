package seedu.duke.exceptions;

/**
 * TaskException
 */
public class TaskException extends Exception {
    private String message;

    public TaskException(String message) {
        super("TaskException: " + message + "\n");
        this.message = message;

    }

    @Override
    public String getMessage() {
        return "message\n";
    }
}
