package evo.exceptions;

/**
 * This exception is thrown when the description and deadline of the Deadline Task added by the user are missing.
 *
 * @author NgChunMan
 */
public class MissingDescriptionAndDeadlineException extends Exception {

    /**
     * Constructs a MissingDescriptionAndDeadlineException object.
     */
    public MissingDescriptionAndDeadlineException() {
        super();
    }

}
