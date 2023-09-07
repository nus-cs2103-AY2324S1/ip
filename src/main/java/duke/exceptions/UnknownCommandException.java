package duke.exceptions;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I'm sorry, I don't know what that means!");
    }
}
