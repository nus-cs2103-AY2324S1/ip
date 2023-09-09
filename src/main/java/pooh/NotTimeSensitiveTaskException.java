package pooh;

/**
 * Exception thrown when specific tasks that are not time-sensitive.
 * <p>
 * This exception is thrown when an operation that requires a time-sensitive task
 * is performed on a task that is not time-sensitive.
 * </p>
 */
public class NotTimeSensitiveTaskException extends PoohException {

    /**
     * Returns a string representation of this exception.
     *
     * @return A string indicating that the task is not time-sensitive.
     */
    @Override
    public String toString() {
        return "Task is not time-sensitive!";
    }
}
