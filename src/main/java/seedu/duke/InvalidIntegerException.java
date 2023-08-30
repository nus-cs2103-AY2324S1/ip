package seedu.duke;
public class InvalidIntegerException extends DukeException {
    public InvalidIntegerException() {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! There is no such task.\n" +
                " Please input an integer.\n" +
                "____________________________________________________________");
    }
}
