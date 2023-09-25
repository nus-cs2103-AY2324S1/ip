package thea;

/**
 * Represents an exception to use when users try to change a task that does not exist.
 */
public class IndexOutOfBoundsException extends Exception {
    /**
     * Constructs a new IndexOutOfBoundsException object.
     *
     * @param errorMessage errorMessage of the exception.
     */
    public IndexOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}
