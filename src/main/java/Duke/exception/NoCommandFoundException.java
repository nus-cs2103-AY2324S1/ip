package Duke.exception;

import Duke.message.ErrorMessage;

/**
 * An exception that is thrown when a no matching command is found.
 */
public class NoCommandFoundException extends DukeException {
    public NoCommandFoundException(String message){
        super(message);
    }

    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage("Sorry, I don't know what " + content + " means.");
    }
}

