package duke.exception;

/**
 * Represents an duke.exception that is thrown when the application encounters an invalid date and time input.
 * <p>
 * This duke.exception is triggered when the user provides a date
 * and time format that does not match the expected formats:
 * "yyyy-MM-dd" or "yyyy-MM-dd HHmm".
 * </p>
 */
public class InvalidDateTimeException extends Exception {

    /**
     * Constructs a new InvalidDateTimeException with a default error message.
     */
    public InvalidDateTimeException() {
        super("☹ OOPS!!! I'm sorry, but the input of date and time is invalid."
                + " Please follow the format of yyyy-MM-dd or yyyy-MM-dd HHmm :-(");
    }
}
