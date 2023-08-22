/**
 * This is the DukeException class for exceptions specific to the Duke program
 * @author Selwyn
 */
public class DukeException extends Exception {
    /**
     * Constructor for the DukeException class
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     * @return String Exception message
     */
    @Override
    public String getMessage() {
        return "OOPS! " + super.getMessage();
    }
}
