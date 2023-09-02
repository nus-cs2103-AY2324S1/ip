package evo.exceptions;

/**
 * This exception is thrown when user tries to delete a task from an empty taskList.
 */
public class NoTaskException extends Exception {

    /**
     * Constructs a NoTaskException object.
     */
    public NoTaskException() {
        super();
    }

}
