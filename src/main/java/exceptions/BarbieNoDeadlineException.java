package exceptions;

/**
 * Throws an exception for a Deadline initiated with no deadline.
 */
public class BarbieNoDeadlineException extends BarbieException {

    /**
     * Constructor for BarbieNoDeadlineException.
     */
    public BarbieNoDeadlineException() {
        super("Barbie your deadline has no deadline!\n"
                + "Remember to add a deadline after the description denoted by a '/' luv");
    }
}
