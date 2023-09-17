package bob.exception;

/**
 * Exception for missing task description
 */
public class MissingTaskException extends BobException {
    public String message = "Do provide a task description.";
}
