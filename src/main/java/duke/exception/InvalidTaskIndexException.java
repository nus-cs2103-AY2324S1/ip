package Duke.exception;

import Duke.message.ErrorMessage;
/**
 *
 * An exception that is thrown when a task index is invalid.
 */
public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException(String message) {
        super(message);
    }

    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage(content + " isn't a number.");
    }
}
