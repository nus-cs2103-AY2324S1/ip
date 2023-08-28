/**
 * Exception class that indicates the input command doesn't exist
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
