package duck;

/**
 * Represents an exception specific to Duck.
 */
public class DuckException extends Exception {
    /**
     * Initialises a new DuckException.
     * 
     * @param message The error message.
     */
    public DuckException(String message) {
        super(message);
    }
}
