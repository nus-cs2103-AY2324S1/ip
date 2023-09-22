package dude.exception;

/**
 * Exception for invalid sorting parameter in sort command.
 */
public class InvalidSortByTypeException extends DudeException {
    /**
     * Constructs new InvalidSortByTypeException.
     */
    public InvalidSortByTypeException() {
        super("I don't know how to sort that way. (Available options: date, description, type)");
    }
}
