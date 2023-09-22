package dude.exception;

/**
 * Exception for missing sorting parameter in sort command.
 */
public class SortByTypeMissingException extends DudeException {
    /**
     * Constructs new SortByTypeMissingException.
     */
    public SortByTypeMissingException() {
        super("Please specify how to sort. (Available options: date, description, type)");
    }
}
