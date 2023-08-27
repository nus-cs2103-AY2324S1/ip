package exceptions;

public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("Invalid date format.");
    }
}
