package emiya.emiyaexception;

/**
 * An exception that is thrown when the unmark command is used by the user
 * without providing an index for the list.
 */
public class EmptyUnmarkException extends EmiyaException {
    public EmptyUnmarkException() {
        super("Please give a list index for unmark operations!\n");
    }
}
