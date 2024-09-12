package duke.exception;

/**
 * Represents an exception that is thrown when an invalid date and time format is provided.
 */
public class InvalidDateFormatException extends DukeException {

    /**
     * Constructs a new InvalidDateFormatException with a default error message.
     */
    public InvalidDateFormatException() {
        super("Invalid date and time format. Please use the format: yyyy-MM-dd HHmm");
    }
}
