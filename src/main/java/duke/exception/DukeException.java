package Duke.exception;

import Duke.message.ErrorMessage;

/**
 * An abstract class for custom exceptions specific to Duke.
 */
public abstract class DukeException extends Exception{
    protected DukeException(String message) {
        super(message);
    }

    /**
     * Generates an error message object based on the exception's content.
     *
     * @param content Additional content related to the error.
     * @return An ErrorMessage object representing the error message.
     */
    public abstract ErrorMessage generateErrorMessage(String content);
}

