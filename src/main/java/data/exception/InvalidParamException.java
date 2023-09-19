package data.exception;

/**
 * Custom exception class to indicate an
 * invalid parameter was passed in
 * by the user when running a command.
 */
public class InvalidParamException extends DukeException {
    public InvalidParamException(String[] msg) {
        super(msg);
    }

    public InvalidParamException(String msg) {
        super(msg);
    }
}

