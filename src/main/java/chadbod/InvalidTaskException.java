package chadbod;

/**
 * Custom exception class for handling invalid task exceptions in the ChadBod application.
 */
public class InvalidTaskException extends ChadBodException {
    public InvalidTaskException(String message) {
        super(message);
    }
}
