package duke.exception;

/**
 * Represents a duke.exception.DuplicateTaskException.
 *
 * @author Pearlynn
 */

public class DuplicateTaskException extends Exception {

    /**
     * Constructor for duke.exception.DuplicateTaskException class.
     */
    public DuplicateTaskException() {
        super("☹ OOPS!!! There's a duplicate task in your list.");
    }
}
