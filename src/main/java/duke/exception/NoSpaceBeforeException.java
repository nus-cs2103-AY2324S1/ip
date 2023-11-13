package duke.exception;

/**
 * Represents a duke.exception.NoSpaceBeforeException.
 *
 * @author Pearlynn
 */

public class NoSpaceBeforeException extends Exception {

    /**
     * Constructor for duke.exception.NoSpaceBeforeException class.
     *
     * @param word The word without a space before.
     */
    public NoSpaceBeforeException(String word) {
        super("☹ OOPS!!! Pls add a space before typing " + word + ".");
    }
}
