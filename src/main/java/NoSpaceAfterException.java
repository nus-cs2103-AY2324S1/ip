/**
 * Represents a NoSpaceAfterException.
 *
 * @author Pearlynn
 */

public class NoSpaceAfterException extends Exception {

    /**
     * Constructor for NoSpaceAfterException class.
     *
     * @param word The word without a space after.
     */
    public NoSpaceAfterException(String word) {
        super("☹ OOPS!!! Pls add a space after typing " + word + ".");
    }
}
