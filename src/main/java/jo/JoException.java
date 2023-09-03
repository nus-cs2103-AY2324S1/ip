package jo;

/**
 * A custom exception class for handling exceptions specific to the `Jo` application.
 * It extends the standard Java `Exception` class to provide custom error messages.
 */
public class JoException extends Exception {
    public JoException(String message) {
        super(message);
    }
}
