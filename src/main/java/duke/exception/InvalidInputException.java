package duke.exception;

public class InvalidInputException extends DukeException {
    public InvalidInputException() {
        super();
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
