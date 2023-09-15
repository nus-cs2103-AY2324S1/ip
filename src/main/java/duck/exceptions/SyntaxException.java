package duck.exceptions;

/**
 * Throws error message due to invalid Syntax written in the command.
 */
public class SyntaxException extends DuckException {

    public SyntaxException(String message) {
        super(message);
    }
}
