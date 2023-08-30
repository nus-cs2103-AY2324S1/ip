package seedu.duke;
public class InvalidDataFormatException extends DukeException{
    public InvalidDataFormatException() {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! The saved data format is invalid!\n" +
                "____________________________________________________________");
    }
}
