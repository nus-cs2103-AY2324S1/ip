/**
 * Represents a NoSpaceBeforeException.
 *
 * @author Pearlynn
 */

public class NoSpaceBeforeException extends Exception {

    /**
     * Constructor for NoSpaceBeforeException class.
     *
     * @param word The word without a space before.
     */
    public NoSpaceBeforeException(String word) {
        super("☹ OOPS!!! Pls add a space before typing " + word + ".");
    }
}
