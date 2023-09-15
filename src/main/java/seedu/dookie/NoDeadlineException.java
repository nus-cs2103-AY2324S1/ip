package seedu.dookie;

/**
 * Encapsulates the exception where the deadline for a Deadline event is not specified.
 */
public class NoDeadlineException extends DookieException {
    /**
     * Creates a NoDeadlineException instance.
     */
    public NoDeadlineException() {
        super("\u2639 OOPS!!! There is no deadline given.\n" +
                "Please input the duration in the following format:\n" +
                "/from DD-MM-YYYY TTTT /to DD-MM-YYYY TTTT");
    }
}
