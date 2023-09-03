package jeeves.exception;

/**
 * Custom exception that is thrown when there is no data provided after a '/by ' 
 * Encountered when adding a new deadline task and no 'by date' is provided by the user
 */
public class MissingByException extends Exception {

    /**
     * Default constructor for MissingByException.
     * Calls the super constructor with the custom error message
     */
    public MissingByException() {
        super("I cannot do that as the deadline has not been provided.\n"
                + "Please add ' /by <YYYY-MM-DD>' after the task description\n");
    }
}
