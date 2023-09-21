package duke.exception;

public class EmptyTaskException extends DukeException {
    public EmptyTaskException() {
        super("    TWEET!!! A task cannot be empty\n");
    }
}
