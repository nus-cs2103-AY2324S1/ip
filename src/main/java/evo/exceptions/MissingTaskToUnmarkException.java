package evo.exceptions;

/**
 * This exception is thrown when the user never specifies which task to unmark.
 */
public class MissingTaskToUnmarkException extends Exception {

    /**
     * Constructs a MissingTaskToUnmarkException object.
     */
    public MissingTaskToUnmarkException() {
        super();
    }

}
