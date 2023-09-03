package jeeves.exception;

/**
 * Custom exception that is thrown when there is no description provided for a task object
 * Encountered when adding any new task object and no description is provided
 */
public class MissingDescriptionException extends Exception {

    /**
     * Default constructor for MissingDescriptionException.
     * Calls the super constructor with the custom error message
     * 
     * @param errorMsg The custom error message
     */
    public MissingDescriptionException(String errorMsg) {
        super(errorMsg);
    }
}
