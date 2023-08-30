package seedu.duke;
public class InvalidDescriptionException extends DukeException {
    public InvalidDescriptionException(String taskType) {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n" +
                "____________________________________________________________");
    }
}
