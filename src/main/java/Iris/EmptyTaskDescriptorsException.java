package iris;

/**
 * An exception class representing a situation where the description of a todo task is empty.
 */
public class EmptyTaskDescriptorsException extends IrisException {

    /**
     * Returns a string representation of the exception message.
     *
     * @return A string describing the exception.
     */
    @Override
    public String toString() {
        return "The description of a todo cannot be empty.";
    }
}
