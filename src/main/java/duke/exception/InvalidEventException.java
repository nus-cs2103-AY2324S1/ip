package duke.exception;

public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super();
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of an event cannot be empty.";
    }
}

