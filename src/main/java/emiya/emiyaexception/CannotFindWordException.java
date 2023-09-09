package emiya.emiyaexception;

/**
 * An exception that is thrown when the program is unable to find a specified word from the task list.
 */
public class CannotFindWordException extends EmiyaException {
    public CannotFindWordException() {
        super("Cannot find word given in task list!\n");
    }
}
