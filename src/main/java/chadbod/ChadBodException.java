package chadbod;
/**
 * The class ChadBodException represents a custom exception class for handling ChadBod-related
 * exceptions.
 */
public class ChadBodException extends Exception {
    /**
     * Constructs an instance of ChadBodException with the given message.
     *
     * @param message A descriptive message explaining the exception.
     */
    public ChadBodException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
