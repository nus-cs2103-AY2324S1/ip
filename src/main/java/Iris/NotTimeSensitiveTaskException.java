package iris;

/**
 * An exception class representing a situation where a task is not time-sensitive.
 */
public class NotTimeSensitiveTaskException extends InvalidTaskException {
    /**
     * Returns a string representation of the exception message.
     *
     * @return A string describing the exception.
     */
    @Override
    public String toString() {
        return "Task is not time-sensitive!";
    }
}
