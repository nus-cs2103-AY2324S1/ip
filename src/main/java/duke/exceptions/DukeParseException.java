package duke.exceptions;

/**
 * Parse exception for Duke.
 */
public class DukeParseException extends DukeException {
    public DukeParseException(String task) {
        super("OOPS!!! Missing parameters in " + task);
    }
}
