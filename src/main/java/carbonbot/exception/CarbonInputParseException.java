package carbonbot.exception;

/**
 * Thrown to indicate the input to CarbonBot could not be parsed successfully.
 */
public class CarbonInputParseException extends CarbonException {

    /**
     * Constructs a CarbonInputParseException.
     */
    public CarbonInputParseException(String message) {
        super("The input provided was invalid. " + message);
    }
}
