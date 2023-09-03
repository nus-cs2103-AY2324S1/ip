package jeeves.exception;

/**
 * Custom exception that is thrown when a 'null' is met instead of a Task object.
 * nulls are used to represent a deleted task, so essentially this exception is thrown
 * when an operation is being attempted on a task that has been deleted from the list.
 */
public class DeletedIdException extends Exception {

    /**
     * Default constructor for DeletedIdException.
     * Calls the super constructor with the custom error message
     */
    public DeletedIdException() {
        super("I cannot do that as that is not a valid Task ID "
                + "(ID provided belongs to a deleted task)\n");
    }
}