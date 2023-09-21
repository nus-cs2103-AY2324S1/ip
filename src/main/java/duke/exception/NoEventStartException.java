package duke.exception;

public class NoEventStartException extends DukeException {

    public NoEventStartException() {
        super("    TWEET!!! An event cannot have no start timing");
    }
}
