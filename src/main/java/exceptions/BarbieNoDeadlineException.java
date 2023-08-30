package exceptions;

import exceptions.BarbieException;

public class BarbieNoDeadlineException extends BarbieException {
    public BarbieNoDeadlineException() {
        super("Barbie your deadline has no deadline!\n"
                + "Remember to add a deadline after the description denoted by a '/' luv");
    }
}
