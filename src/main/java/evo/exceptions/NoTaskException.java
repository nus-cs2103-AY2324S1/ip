package evo.exceptions;

/**
 * This exception is thrown when user tries to delete a task from an empty taskList.
 *
 * @author NgChunMan
 */
public class NoTaskDeleteException extends Exception {

    /**
     * Constructs a NoTaskException object.
     */
    public NoTaskDeleteException() {
        super();
    }

}
