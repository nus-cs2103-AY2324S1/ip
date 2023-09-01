package duke.exception;

public class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("Oh no! Bobi cannot receive empty commands. :/");
    }
}
