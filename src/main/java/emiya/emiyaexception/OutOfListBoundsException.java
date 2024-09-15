package emiya.emiyaexception;

/**
 * An exception that is thrown when the user tries to access a task that does not exist.
 */
public class OutOfListBoundsException extends EmiyaException {
    public OutOfListBoundsException() {
        super("Task does not exist! Please try with a different value\n");
    }
}
