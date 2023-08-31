package bruno.exceptions;

/**
 * The BrunoIntegerMismatchException handles the case where the argument for mark, unmark or delete is not
 * an integer value.
 */
public class BrunoIntegerMismatchException extends BrunoException {

    private String activity;

    public BrunoIntegerMismatchException(String activity) {
        super("Ruff Ruff! Tasks to be " + activity + "ed can only be integers! ❌");
    }
}
