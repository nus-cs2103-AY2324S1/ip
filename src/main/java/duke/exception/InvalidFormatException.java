package duke.exception;

/**
 * Represents an exception that is thrown when the format of the command is invalid.
 */
public class InvalidFormatException extends DukeException {

    /**
     * Constructs a new InvalidFormatException with a default error message.
     */
    public InvalidFormatException(String message) {
        super("The format of the command is invalid. " + message
                + " Please check the User Guide for more information.");
    }
}
