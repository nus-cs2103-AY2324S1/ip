package emiya.emiyaexception;

/**
 * An exception that is thrown when the user uses a delete command without providing a list index.
 */
public class EmptyDeleteException extends EmiyaException {
    public EmptyDeleteException() {
        super("Please give a list index for delete operations!\n");
    }
}
