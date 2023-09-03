package jeeves.exception;

/**
 * Custom exception that is thrown when there is no data provided after a '/from ' 
 * Encountered when adding a new event task and no 'from date' is provided by the user
 */
public class MissingFromException extends Exception {

    /**
     * Default constructor for MissingFromException.
     * Calls the super constructor with the custom error message
     */
    public MissingFromException() {
        super("I cannot do that as the start time has not been provided.\n"
                + "Please add ' /from <Time/Date>' after the task description\n");
    }
}
