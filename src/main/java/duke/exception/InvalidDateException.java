package duke.exception;

public class InvalidDateException extends DukeException {

    public InvalidDateException() {
        super("☹ OOPS!!! Please enter a valid date in yyyy-mm-dd format.");
    }

}
