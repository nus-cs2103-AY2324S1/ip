package bongo.helper;

public class BongoException extends Exception {
    /**
     * A constructor for a BongoException.
     */
    public BongoException() {
        super("I'm sorry, but I don't know what that means :-(");
    }

    public BongoException(String message) {
        super(message);
    }
}
