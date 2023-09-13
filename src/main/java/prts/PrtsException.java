package prts;

/**
 * A general exception for any errors that PRTS may encounter in its execution. All other custom
 * exceptions in PRTS extend from this class.
 */
public class PrtsException extends Exception {

    /**
     * Constructs a PrtsException object.
     * @param message The error message to be displayed.
     */
    public PrtsException(String message) {
        super(message);
    }

}
