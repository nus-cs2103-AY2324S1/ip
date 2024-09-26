package Eddie.Exceptions;

import Eddie.Exceptions.DukeException;

/**
 * Exception for when user does not provide from date.
 */
public class MissingFromDateException extends DukeException {
    public MissingFromDateException() {
        super("Please use /from to include start date");
    }
}
