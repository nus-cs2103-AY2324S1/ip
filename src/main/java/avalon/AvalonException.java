package avalon;

/**
 * Custom exception class for Avalon.
 * Represents exceptions specific to the Avalon chatbot application.
 */
public class AvalonException extends Exception {

    /**
     * Constructs an AvalonException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public AvalonException(String message) {
        super(message);
    }
}
