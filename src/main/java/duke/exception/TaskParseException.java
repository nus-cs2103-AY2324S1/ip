package duke.exception;

/**
 * Represents an exception thrown when there's an error in parsing a task in Duke.
 */
public class TaskParseException extends DukeException {
    public TaskParseException(String message) {
        super(message);
    }
}
