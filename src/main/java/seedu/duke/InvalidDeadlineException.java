package seedu.duke;

/**
 * Encapsulates the exception where the deadline inputted does not contain a valid dead and/or time.
 */
public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException(String deadline) {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! " + deadline + " is not a valid deadline.\n" +
                "____________________________________________________________");
    }
}
