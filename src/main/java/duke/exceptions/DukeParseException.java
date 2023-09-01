package duke.exceptions;

public class DukeParseException extends DukeException {
    public DukeParseException(String task) {
        super("OOPS!!! Missing parameters in " + task);
    }
}
