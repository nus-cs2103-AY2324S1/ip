package Duke.exception;

import Duke.message.ErrorMessage;

public class EmptyTaskDescException extends DukeException {
    public EmptyTaskDescException(String message) {
        super(message);
    }

    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage("A task description cannot be empty.");
    }
}


