package Duke.Exceptions;

/**
 * The InvalidInput class represents an exception thrown when the user input is invalid i.e not recognized.
 */
public class InvalidInput extends Exception {
    public InvalidInput(String x) {
        super(x);
    }
}
