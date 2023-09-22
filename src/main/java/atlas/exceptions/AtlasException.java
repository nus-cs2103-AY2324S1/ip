package atlas.exceptions;

/**
 * Exception class for exceptions that terminate the command and meant to be handled by Atlas
 */
public class AtlasException extends RuntimeException {

    /**
     * Constructs a AtlasException exception
     * @param errorDetails String containing information about error
     */
    public AtlasException(String errorDetails) {
        super(errorDetails);
    }
}
