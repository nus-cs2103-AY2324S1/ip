package duke.exceptions;

/**
 * Exception class that indicates the task has no name
 */
public class MissingTaskNameException extends DukeException {
    public MissingTaskNameException(String errorMessage) {
        super(errorMessage);
    }
}