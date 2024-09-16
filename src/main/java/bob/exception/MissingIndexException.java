package bob.exception;

/**
 * Exception when index is not given for commands that require them
 * e.g. delete, mark, unmark
 */
public class MissingIndexException extends BobException {
    public String message = "Do provide the task index.";
}
