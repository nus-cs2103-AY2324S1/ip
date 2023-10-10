package potato;


/**
 * The PotatoException class is a custom runtime exception.
 * It extends the RuntimeException class.
 */
public class PotatoException extends RuntimeException {

    /**
     * Constructs a new PotatoException with the specified error message.
     *
     * @param message A String containing the error message that describes the exceptional situation.
     */
    public PotatoException(String message) {
        super(message);
    }
}
