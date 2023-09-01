package duke.exception;

public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("The description of a todo cannot be empty.");
    }
}
