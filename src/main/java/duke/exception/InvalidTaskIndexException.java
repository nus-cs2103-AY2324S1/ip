package duke.exception;

import duke.message.ErrorMessage;

/**
 * An exception that is thrown when a task index is invalid.
 */
public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException(String message) {
        super(message);
    }

    @Override
    public ErrorMessage generateErrorMessage() {
        return new ErrorMessage(getMessage() + " isn't a number.");
    }
}
