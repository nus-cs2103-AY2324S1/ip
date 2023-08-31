package exception;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super();
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }
}

