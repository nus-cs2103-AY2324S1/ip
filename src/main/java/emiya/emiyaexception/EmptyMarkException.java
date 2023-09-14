package emiya.emiyaexception;

/**
 * An exception that is thrown when the mark command is used by the user
 * without providing an index for the list.
 */
public class EmptyMarkException extends EmiyaException {
    public EmptyMarkException() {
        super("Please give a list index for mark operations!\n");
    }
}
