package seedu.duke;
public class InvalidDurationException extends DukeException {
    public InvalidDurationException(String duration) {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! " + duration + " is not a valid duration.\n" +
                "____________________________________________________________");
    }
}
