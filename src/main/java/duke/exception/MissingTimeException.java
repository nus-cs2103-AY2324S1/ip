package duke.exception;

public class MissingTimeException extends DukeException {
    public MissingTimeException() {
        super("Please input a date or time for this task.");
    }
}
