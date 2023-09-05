package thea;

/**
 * Represents an exception to use when users do not write description of tasks.
 */
public class EmptyDescriptionException extends Exception{
    /**
     * Constructs a new EmptyDescriptionException object.
     *
     * @param errorMessage errorMessage of the exception.
     */
    public EmptyDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
