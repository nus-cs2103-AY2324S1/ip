package exception;

/**
 * The EmptyDateTimeException is thrown when the user types in an empty date/time into
 * the input command when a date/time is required.
 */
public class EmptyDateTimeException extends DukeException {
    protected String message;

    /**
     * The constructor of EmptyDateTimeException.
     *
     * @param message The error message.
     */
    public EmptyDateTimeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "☹ OOPS!!! The date/time of the " + this.message + " cannot be empty."
                + "\n-----------------------------------------------------------------";
    }
}
