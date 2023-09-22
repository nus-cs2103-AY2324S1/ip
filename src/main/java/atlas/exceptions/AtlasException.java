package atlas.exceptions;

/**
 * Exception class for exceptions that terminate the command and meant to be handled by Atlas
 */
public class AtlasException extends RuntimeException {
    static final String EXCEPTION_MESSAGE_PREFIX = "Sorry, I ran into an error! Here's more info:\n";

    /**
     * Constructs a AtlasException exception
     * @param errorDetails String containing information about error
     */
    public AtlasException(String errorDetails) {
        super(errorDetails);
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE_PREFIX + super.getMessage();
    }
}
