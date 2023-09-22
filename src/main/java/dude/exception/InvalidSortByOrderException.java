package dude.exception;

/**
 * Exception for invalid sorting order in sort command.
 */
public class InvalidSortByOrderException extends DudeException {
    /**
     * Constructs new InvalidSortByOrderException.
     */
    public InvalidSortByOrderException() {
        super("I don't know how to sort that way. (Available options: asc [ascending], desc [descending])");
    }
}
