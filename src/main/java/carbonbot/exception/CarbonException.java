package carbonbot.exception;

/**
 * Thrown to indicate that an exception occurred that is specific to Duke.
 */
public class CarbonException extends Exception {

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param errorMessage Error Message
     */
    public CarbonException(String errorMessage) {
        super(errorMessage);
    }
}
