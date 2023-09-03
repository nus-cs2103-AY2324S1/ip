package jeeves.exception;

/**
 * Custom exception that is thrown when there is no valid id provided when using a command that requires an id input. 
 * Encountered when attempting to mark, unmark or delete a task.
 */
public class MissingIdException extends Exception {

    /**
     * Default constructor for MissingIdException.
     * Calls the super constructor with the custom error message
     */
    public MissingIdException() {
        super("I cannot do that as you have not provided me with a Task ID\n");
    }
}
