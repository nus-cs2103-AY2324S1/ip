/**
 * Exception class that indicates the task number is out of bounds
 */
public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException(String errorMessage) {
        super(errorMessage);
    }
}
