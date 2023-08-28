/**
 * Exception class that indicates the task is missing its description
 */
public class MissingTaskDescriptionException extends Exception {
    public MissingTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}