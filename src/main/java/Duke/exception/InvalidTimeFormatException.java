package Duke.exception;

import Duke.message.ErrorMessage;

/**
 * An exception that is thrown when a time format is invalid.
 */
public class InvalidTimeFormatException extends DukeException{
    public InvalidTimeFormatException(String message) {
        super(message);
    }

    @Override
    public ErrorMessage generateErrorMessage(String content) {
        return new ErrorMessage(content + " isn't the right time format.");
    }
}
