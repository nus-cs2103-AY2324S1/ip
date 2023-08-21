/**
 * Represents a NoDescriptionException.
 *
 * @author Rayson
 */
public class NoDescriptionException extends Exception {

    public NoDescriptionException(String message) {
        super(String.format("☹ OOPS!!! Where is your description for %s?", message));
    }

}
