package bob.exception;

/**
 * Exception when dates are not provided or in wrong format
 * for Events and Deadlines
 */
public class MissingDatesException extends BobException {
    public String message = "Please provide the appropriate date!";
}
