package seedu.duke;

/**
 * Encapsulates the exception where the deadline inputted does not contain a valid dead and/or time.
 */
public class InvalidDeadlineException extends DukeException {
    /**
     * Creates a new InvalidDeadlineException instance.
     *
     * @param deadline The deadline given by the user.
     */
    public InvalidDeadlineException(String deadline) {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! " + deadline + " is not a valid deadline.\n" +
                "Please input the deadline in the following format:\n" +
                "/DD-MM-YYYY TTTT\n" +
                "____________________________________________________________");
    }
}
