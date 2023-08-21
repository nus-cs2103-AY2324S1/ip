package exceptions;

public class MissingIndexException extends DukeException {
    public MissingIndexException() {
        super("Missing task index for marking task as done/not done.");
    }
}
