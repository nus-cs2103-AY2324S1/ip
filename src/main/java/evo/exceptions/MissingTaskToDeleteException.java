package evo.exceptions;

/**
 * This exception is thrown when the user never specifies which task to delete.
 */
public class MissingTaskToDeleteException extends Exception {

    /**
     * Constructs a MissingTaskToDeleteException object.
     */
    public MissingTaskToDeleteException() {
        super();
    }
}
