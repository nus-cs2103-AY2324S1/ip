package deterministicparrot;

/**
 * Custom exception class for the Deterministic Parrot application.
 * This exception is thrown to indicate errors specific to the application.
 */
public class DeterministicParrotException extends Exception {
    /**
     * Constructs a new DeterministicParrotException with the specified detail message.
     *
     * @param message The detail message that describes the exception.
     */
    public DeterministicParrotException(String message) {
        super(message);
    }
}
