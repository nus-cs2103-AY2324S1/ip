package iris;

/**
 * An exception class representing a situation where an error has occurred whilst writing to file.
 */
public class WriteTaskException extends IrisException {
    /**
     * Returns a string representation of the exception message.
     *
     * @return A string describing the exception.
     */
    @Override
    public String toString() {
        return "An error has occurred whilst writing to file.";
    }
}
