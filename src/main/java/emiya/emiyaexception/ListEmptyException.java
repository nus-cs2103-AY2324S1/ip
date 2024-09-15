package emiya.emiyaexception;

/**
 * An exception that is thrown when the user tries to access the list when the list is empty.
 */
public class ListEmptyException extends EmiyaException {
    public ListEmptyException() {
        super("List is empty! Please add items to list before trying to display list contents!\n");
    }
}
