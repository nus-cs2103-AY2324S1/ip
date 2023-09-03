package jeeves.exception;

/**
 * Custom exception that is thrown when there is no data provided after a '/to ' 
 * Encountered when adding a new event task and no 'to date' is provided by the user
 */
public class MissingToException extends Exception {

    /**
     * Default constructor for MissingToException.
     * Calls the super constructor with the custom error message
     */
    public MissingToException() {
        super("I cannot do that as the end time has not been provided.\n"
                + "Please add ' /to <Time/Date>' after the task end date (after /from block)\n");
    }
}
