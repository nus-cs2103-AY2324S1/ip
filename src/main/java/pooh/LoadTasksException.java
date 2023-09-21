package pooh;

/**
 * Exception thrown when there is an error loading tasks from a file.
 * <p>
 * This exception is a subclass of PoohException and overrides the toString method to provide
 * a custom error message indicating that there was an error loading tasks from a file.
 * </p>
 */
public class LoadTasksException extends PoohException {

    /**
     * Returns a string representation of the exception, containing a custom error message.
     *
     * @return Custom error message indicating that an error has occurred while loading tasks from a file.
     */
    @Override
    public String toString() {
        return "      An error has occurred whilst loading tasks from file. :-(";
    }
}
