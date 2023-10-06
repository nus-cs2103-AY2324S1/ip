package seedu.duke.exceptions;

/**
 * The KeywordNotFoundException is thrown when there are no task with
 * the corresponding keyword as task description.
 */
public class KeywordNotFoundException extends LemonException {
    public KeywordNotFoundException(String message) {
        super(message);
    }
}
