package simon;

/**
 * The {@code SimonException} class represents exceptions specific to the Simon application.
 * This custom exception class extends the Java {@link Exception} class and is used
 * to handle and represent errors that can occur during the operation of the Simon application.
 */
public class SimonException extends Exception {

    /**
     * Constructs a new {@code SimonException} with the specified detail message.
     *
     * @param message The detail message which provides more information about the error.
     */
    public SimonException(String message) {
        super(message);
    }
}
