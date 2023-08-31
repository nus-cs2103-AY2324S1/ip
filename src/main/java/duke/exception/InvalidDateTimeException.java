package duke.exception;

public class InvalidDateTimeException extends DukeException {
    public InvalidDateTimeException() {
        super();
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The datetime format is invalid.";
    }
}

