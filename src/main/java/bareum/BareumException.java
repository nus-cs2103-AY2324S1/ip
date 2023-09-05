package bareum;

/**
 * A custom exception for encapsulating exceptions unique to the Bareum program.
 */
public class BareumException extends Exception {
    /**
     * Creates a new BareumException instance with the corresponding exception message.
     * @param message The details of the exception.
     */
    public BareumException(String message) {
        super("Bareum: " + message);
    }
}
