package seedu.dookie;

/**
 * Encapsulates the exception where the deadline inputted does not contain a valid dead and/or time.
 */
public class InvalidDeadlineException extends DookieException {
    /**
     * Creates a new InvalidDeadlineException instance.
     *
     * @param deadline The deadline given by the user.
     */
    public InvalidDeadlineException(String deadline) {
        super("\u2639 OOPS!!! " + deadline + " is not a valid deadline.\n" +
                "Please input the deadline in the following format:\n" +
                "/DD-MM-YYYY TTTT");
    }
}
