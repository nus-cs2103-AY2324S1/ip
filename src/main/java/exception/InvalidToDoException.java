package exception;

public class InvalidToDoException extends DukeException {
    public InvalidToDoException() {
        super();
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
