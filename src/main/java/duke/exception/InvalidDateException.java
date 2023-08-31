package duke.exception;
public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("☹ OOPS!!! I'm sorry, but your date is in the incorrect format! " +
                "please key it in yyyy-mm-dd format! :-(");
    }
}
