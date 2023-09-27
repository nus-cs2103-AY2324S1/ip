package didier.exception;

/**
 * Thrown to indicate that the user command inputted is invalid.
 */
public class InvalidCommandException extends DidierException {

    public InvalidCommandException(String command) {
        super("I don't quite understand " + command + ". ");
    }
}
