package exceptions;

import exceptions.BarbieException;

public class BarbieNoTimingException extends BarbieException {
    public BarbieNoTimingException() {
        super("Barbie your party has the incorrect number of timings!\n"
                + "Remember to add a 'start' time and an 'end' time"
                + " after the description! denote it by a '/' luv");
    }
}
