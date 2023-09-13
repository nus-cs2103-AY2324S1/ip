package prts;

/**
 * Signals that the input value is out of range of the list.
 */
public class OutOfRangeException extends PrtsException {

    /**
     * Constructs a OutOfRangeException.
     */
    public OutOfRangeException() {
        super("Please provide a number within range.");
    }

}
