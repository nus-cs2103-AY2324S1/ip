package barbie.exceptions;

/**
 * Represents the exception when there is an empty list accessed.
 */
public class BarbieListEmptyException extends BarbieException {
    /**
     * Constructs an instance of a BarbieListEmptyException.
     */
    public BarbieListEmptyException() {
        super("Hmm.. your list looks empty. To add items, use the 'todo', "
                + "'deadline' or 'party' commands!");
    }
}
