package duke.exception;

public class EmptyTaskException extends DukeException {
    public EmptyTaskException() {
        super("Oh no! Bobi cannot add an empty task. :/");
    }
}
