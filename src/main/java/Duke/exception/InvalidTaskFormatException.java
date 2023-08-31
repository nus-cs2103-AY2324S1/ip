package Duke.exception;

import Duke.message.ErrorMessage;

/**
 * An exception that is thrown when a task format is invalid.
 */
public class InvalidTaskFormatException extends DukeException{
    public InvalidTaskFormatException(String message) {
        super(message);
    }

    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage(content + " isn't the right format for this kind of task.");
    }
}
