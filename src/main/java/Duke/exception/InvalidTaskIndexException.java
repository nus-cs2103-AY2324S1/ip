package Duke.exception;

import Duke.message.ErrorMessage;
public class InvalidTaskIndexException extends DukeException {
    public InvalidTaskIndexException(String message) {
        super(message);
    }

    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage(content + " isn't a number.");
    }
}
