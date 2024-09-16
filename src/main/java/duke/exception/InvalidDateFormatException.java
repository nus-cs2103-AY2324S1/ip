package duke.exception;

/** Exception for invalid date format */
public class InvalidDateFormatException extends DukeException {
    /**
     * Initialize Invalid Date Format Exception.
     *
     * @param message Error message to print out.
     */
    public InvalidDateFormatException(String message) {
        super(message);
    }
}
