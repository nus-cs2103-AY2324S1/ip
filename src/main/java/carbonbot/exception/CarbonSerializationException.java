package carbonbot.exception;

/**
 * Thrown when there is an issue with serializing or deserializing tasks.
 */
public class CarbonSerializationException extends CarbonException {

    /**
     * Constructs a CarbonSerializationException.
     *
     * @param errorMessage Error Message
     */
    public CarbonSerializationException(String errorMessage) {
        super(errorMessage);
    }
}
