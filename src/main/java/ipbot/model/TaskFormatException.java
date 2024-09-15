package ipbot.model;

/**
 * Represents an Exception in parsing the CSV format of a task.
 */
public class TaskFormatException extends Exception {
    public TaskFormatException(String message) {
        super(message);
    }
}
