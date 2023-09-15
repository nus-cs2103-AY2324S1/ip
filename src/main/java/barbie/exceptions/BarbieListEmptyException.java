package barbie.exceptions;

public class BarbieListEmptyException extends BarbieException {
    public BarbieListEmptyException() {
        super("Hmm.. your list looks empty. To add items, use the 'todo', "
                + "'deadline' or 'party' commands!");
    }
}
