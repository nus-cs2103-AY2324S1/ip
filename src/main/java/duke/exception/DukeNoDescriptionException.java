package duke.exception;
public class DukeNoDescriptionException extends DukeException {
    public DukeNoDescriptionException(String msg) {
        super("OOPS!!! The description of a "
                + msg
                + " cannot be empty."
                + "\n");
    }
}
