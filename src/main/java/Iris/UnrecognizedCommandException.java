package iris;

/**
 * An exception class representing a situation where an unrecognized command has been entered.
 */
public class UnrecognizedCommandException extends IrisException {
    /**
     * Returns a string representation of the exception message.
     *
     * @return A string describing the exception.
     */
    @Override
    public String toString() {
        return "I'm sorry but I don't know what that means.";
    }
}
