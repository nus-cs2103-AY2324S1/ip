package seedu.duke;

/**
 * Encapsulates the exception where the task identifier command by the user is not specified.
 */
public class TaskTypeException extends DukeException {
    /**
     * Creates a TaskTypeException instance.
     */
    public TaskTypeException() {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "____________________________________________________________");
    }
}
