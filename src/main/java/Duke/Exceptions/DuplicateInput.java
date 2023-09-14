package Duke.Exceptions;

/**
 * The DuplicateInput class represents an exception thrown when the user input is a duplicate.
 */
public class DuplicateInput extends Exception {
    public DuplicateInput(String x) {
        super(x);
    }
}