package exceptions;

/**
 * Throws an exception for any Task initiated with no description.
 */
public class BarbieNoDescException extends BarbieException {

    /**
     * Constructor for BarbieNoDescException.
     */
    public BarbieNoDescException() {
        super("Barbie your item has no description!\n"
                + "Remember to add a description after the command 'todo/deadline/party'.");
    }

}
