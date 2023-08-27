package duke.exception;

public class DukeFileNotFoundException extends DukeException {
    public DukeFileNotFoundException() {
        super("No available file");
    }
}
