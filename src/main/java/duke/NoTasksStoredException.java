package duke;

/** Exception which occurs when the description of the task is empty. */
public class NoTasksStoredException extends DukeException {
    /**
     * Class constructor containing the message of the exception.
     * @param message the string message associated with the exception.
     */
    public NoTasksStoredException(String message) {
        super(message);
    }
}
