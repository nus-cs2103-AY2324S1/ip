package exception;

public class InvalidListNumberException extends DukeException {
    public InvalidListNumberException() {
        super();
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The task number entered is invalid.";
    }
}

