/**
 * Exception class that indicates the task has no name
 */
public class MissingTaskNameException extends Exception {
    public MissingTaskNameException(String errorMessage) {
        super(errorMessage);
    }
}