package duke.exception;

public class DateRangeException extends DukeException {

    public DateRangeException() {
        super("☹ OOPS!!! End date cannot be before start date.");
    }

}