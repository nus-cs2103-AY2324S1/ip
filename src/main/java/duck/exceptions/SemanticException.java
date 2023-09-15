package duck.exceptions;

/**
 * Throws error message because of the Semantic Error in the application
 */
public class SemanticException extends DuckException {

    public SemanticException(String message) {
        super(message);
    }
}
