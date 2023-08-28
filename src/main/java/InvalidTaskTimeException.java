/**
 * Exception class that indicates there is missing information on time inputs
 */
public class InvalidTaskTimeException extends Exception {
    public InvalidTaskTimeException(String errorMessage) {
        super(errorMessage);
    }
}