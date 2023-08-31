package duke.exception;
public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException(String s) {
        super("☹ OOPS!!! I'm sorry, but arguments to " + s + " cannot be empty");
    }
}