package exceptions;

/**
 * Throws an exception when no timing is given when initiating a Deadline or a Party.
 */
public class BarbieNoTimingException extends BarbieException {

    /**
     * Constructor for BarbieNoTimingException.
     */
    public BarbieNoTimingException() {
        super("Barbie your party has the incorrect number of timings!\n"
                + "Remember to add a 'start' time and an 'end' time"
                + " after the description! denote it by a '/' luv");
    }
}
