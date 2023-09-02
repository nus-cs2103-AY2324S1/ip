package emiya.emiyaexception;

/**
 * An exception that is thrown when the user uses the list command when the list is empty.
 */
public class ListEmptyException extends EmiyaException {
    public ListEmptyException() {
        super("-----------------------------------------\n"
                + "List is empty! Please add items to list before trying to display list contents!\n"
                + "-----------------------------------------\n");
    }
}
