package bruno.exceptions;

/**
 * The BrunoEmptyException handles the case where the description for a new task is empty.
 */
public class BrunoEmptyException extends BrunoException {
    private String type;

    public BrunoEmptyException(String type) {
        super("Ruff Ruff! Description of " + type + " cannot be empty! ❌");
    }
}
