package dukeExceptions;

public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("☹ OOPS!!! Date format given is invalid. Please input date in the following format: yyyy-mm-dd");
    }
}
