package dre.exception;

/**
 * Represents an exception specific to the Dre chatbot application.
 */
public class DreException extends Exception {

    /**
     * Constructs a new DreException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public DreException(String message) {
        super(message);
    }
}
