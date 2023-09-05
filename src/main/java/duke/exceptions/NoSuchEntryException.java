package duke.exceptions;

/**
 * @author juzzztinsoong
 */
public class NoSuchEntryException extends DukeException {
    public NoSuchEntryException() {
        super("There is no such entry!");
    }
}
