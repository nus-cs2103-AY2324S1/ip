package exceptions;

/**
 * Custom exception class for the application.
 *
 * @author Ho Khee Wei
 */
public class ThorndikeException extends Exception {

    /**
     * Constructs a ThorndikeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public ThorndikeException(String message) {
        super("MEOW! " + message);
    }
}
