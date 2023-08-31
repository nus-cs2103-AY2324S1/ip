package exceptions;

/**
 * Represents an exception where there are no arguments in the input
 *
 * @author Andrew Daniel Janong
 */
public class DukeEmptyArgumentException extends DukeException {
    public DukeEmptyArgumentException(String message) {
        super(message);
    }
}
