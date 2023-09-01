package exceptions;

import exceptions.BarbieException;

public class BarbieNoDescException extends BarbieException {
    public BarbieNoDescException() {
        super("Barbie your item has no description!\n"
                + "Remember to add a description after the command 'todo/deadline/party'.");
    }
}
