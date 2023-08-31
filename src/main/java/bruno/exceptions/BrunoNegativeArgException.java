package bruno.exceptions;

/**
 * The BrunoNegativeArgExpression handles the case where the user has entered an argument that is negative,
 * for mark, unmark or delete.
 */
public class BrunoNegativeArgException extends BrunoException {

    private String activity;

    public BrunoNegativeArgException(String activity) {
        super("Ruff Ruff! Task numbers to be " + activity + "ed cannot be negative! ❌");
    }
}
