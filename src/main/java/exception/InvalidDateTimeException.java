package exception;

/**
 * The InvalidDateTimeException is thrown when the user types in an invalid date/time
 * into the input command.
 */
public class InvalidDateTimeException extends DukeException {
    String message;

    /**
     * The constructor of InvalidDateTimeException.
     *
     * @param message The error message.
     */
    public InvalidDateTimeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The date/time format of the "
                + this.message + " is incorrect :((("
                + "\n-----------------------------------------------------------------";
    }
}
