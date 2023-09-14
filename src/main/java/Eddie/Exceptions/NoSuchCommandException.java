package Eddie.Exceptions;

import Eddie.Exceptions.DukeException;

/**
 * Exception for when the user inputs a command which is not recognized.
 */
public class NoSuchCommandException extends DukeException {
    public NoSuchCommandException() {
        super("There is no such command!");
    }
}
