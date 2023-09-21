package jarvis.exception;

/**
 * Represents an exception that occurs when the user provides an invalid index.
 */
public class JarvisInvalidIndexException extends JarvisException {

    /**
     * Creates a new JarvisInvalidIndexException with the specified task index.
     *
     * @param taskIndex The task index that is invalid.
     */
    public JarvisInvalidIndexException(int taskIndex) {
        super("OOPS!!! No such task with index " + taskIndex);
    }
}
