package duke.exception;

/**
 * Represents a duke.exception.NoSpaceAfterException.
 *
 * @author Pearlynn
 */

public class NoSpaceAfterException extends Exception {

    /**
     * Constructor for duke.exception.NoSpaceAfterException class.
     *
     * @param word The word without a space after.
     */
    public NoSpaceAfterException(String word) {
        super("☹ OOPS!!! Pls add a space after typing " + word + ".");
    }
}
