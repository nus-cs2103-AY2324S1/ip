package seedu.duke;
public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException(String deadline) {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! " + deadline + " is not a valid deadline.\n" +
                "____________________________________________________________");
    }
}
