package duke.exception;

public class DukeUnknownCommandException extends DukeException {
    public DukeUnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(\n");
    }
}
