package duke.exceptions;

public class InvalidArgumentException extends DukeException {
    public InvalidArgumentException() {
        super("I'm sorry, but you have entered an invalid argument :-(");
    }
}
