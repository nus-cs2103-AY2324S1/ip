package duke.exception;

/**
 * Represents a duke.exception.EmptyKeywordException.
 *
 * @author Pearlynn
 */

public class EmptyKeywordException extends Exception {

    /**
     * Constructor for duke.exception.EmptyKeywordException class
     */
    public EmptyKeywordException() {
        super("☹ OOPS!!! The search keyword cannot be empty.");
    }
}
