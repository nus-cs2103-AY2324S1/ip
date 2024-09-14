package crusader.exception;

/**
 * Represents an error in parsing user input
 */
public class CrusaderParseException extends CrusaderException {
    public CrusaderParseException(String message) {
        super(String.format("Hey, that command isn't done properly...\nDetails: %s", message));
    }
}
