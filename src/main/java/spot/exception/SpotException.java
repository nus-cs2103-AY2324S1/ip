package spot.exception;

/**
 * Exception thrown when there is an error in the execution of the program.
 */
public class SpotException extends Exception {

    /**
     * Constructs a new SpotException object.
     *
     * @param message Message to be displayed when the exception is thrown.
     */
    public SpotException(String message) {
        super(message);
    }
}
