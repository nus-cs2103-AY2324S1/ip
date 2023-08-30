package duke.exceptions;

/**
 * Exception class that indicates the task is missing its description
 */
public class MissingTaskDescriptionException extends DukeException {
    public MissingTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}