package duke.exception;

public class MissingTimeException extends DukeException {
    public MissingTimeException() {
        super("Please input a date and time for this task.");
    }
}
