package bruno.exceptions;

/**
 * The BrunoIndexOutOfBoundsException handles the case where the user has entered an argument that is
 * greater than the number of tasks in the list, for mark, unmark or delete.
 */
public class BrunoIndexOutOfBoundsException extends BrunoException {

    private String activity;

    /**
     * Displays the error message.
     *
     * @param activity One of mark, unmark or delete.
     */
    public BrunoIndexOutOfBoundsException(String activity) {
        super("Ruff Ruff! Task numbers to be " + activity
                + "ed cannot be greater than number of tasks in the list! ❌");
    }
}
