package Duke.exception;

import Duke.message.ErrorMessage;
public abstract class DukeException extends Exception{
    protected DukeException(String message) {
        super(message);
    }
    public abstract ErrorMessage generateErrorMessage(String content);
}

