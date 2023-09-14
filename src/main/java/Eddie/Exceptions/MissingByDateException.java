package Eddie.Exceptions;

import Eddie.Exceptions.DukeException;

/**
 * Excpetion when user does not provide by date
 */
public class MissingByDateException extends DukeException {
    public MissingByDateException() {
        super("Please use /by to include a due date");
    }
}
