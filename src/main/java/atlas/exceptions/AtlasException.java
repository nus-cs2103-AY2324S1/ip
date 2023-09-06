package atlas.exceptions;

/**
 * Exception class for exceptions that terminate the command and meant to be handled by Duke
 */
public class AtlasException extends RuntimeException {

    /**
     * Constructs a DukeException exception
     * @param errorDetails String containing information about error
     */
    public AtlasException(String errorDetails) {
        super(errorDetails);
    }

    @Override
    public String getMessage() {
        return "Sorry, I ran into an error! Here's more info:\n" + super.getMessage();
    }
}
