package Duke.Exceptions;

/**
 * The IncompleteInput class represents an exception thrown when the user input is incomplete.
 */
public class IncompleteInput extends Exception {
    public IncompleteInput(String x) {
        super(x);
    }
}
