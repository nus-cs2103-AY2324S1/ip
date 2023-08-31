package duke.exceptions;

/**
 * Exception class for exceptions that terminate the command and meant to be handled by Duke
 */
public class DukeException extends RuntimeException {

    /**
     * Constructs a DukeException exception
     * @param errorDetails String containing information about error
     */
    public DukeException(String errorDetails) {
        super(errorDetails);
    }

    @Override
    public String getMessage() {
        return "Sorry, I ran into an error! Here's more info:\n" + super.getMessage();
    }
}
