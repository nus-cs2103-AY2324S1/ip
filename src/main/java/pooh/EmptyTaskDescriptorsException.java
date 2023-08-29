package pooh;

/**
 * Exception thrown when an attempt is made to create a todo task with an empty description.
 * <p>
 * This exception extends from PoohException, and it overrides the toString method to provide
 * a custom error message indicating that the description of a todo cannot be empty.
 * </p>
 */
public class EmptyTaskDescriptorsException extends PoohException {

    /**
     * Returns a string representation of the exception, containing a custom error message.
     *
     * @return Custom error message indicating that the description of a todo cannot be empty.
     */
    @Override
    public String toString() {
        return "      ☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
