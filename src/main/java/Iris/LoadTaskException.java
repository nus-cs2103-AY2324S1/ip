package iris;

/**
 * An exception class representing a situation where an error has occurred whilst loading tasks from file.
 */
public class LoadTaskException extends IrisException {
    /**
     * Returns a string representation of the exception message.
     *
     * @return A string describing the exception.
     */
    @Override
    public String toString() {
        return "An error has occurred whilst loading tasks from file.";
    }
}
