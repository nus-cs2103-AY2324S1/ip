package thea;

/**
 * Represents an exception to use when users writes a wrong command (first word of input).
 */

public class WrongCommandException extends Exception{

    /**
     * Constructs a new WrongCommandException object.
     *
     * @param errorMessage errorMessage of the exception.
     */
    public WrongCommandException(String errorMessage) {
        super(errorMessage);
    }
}
