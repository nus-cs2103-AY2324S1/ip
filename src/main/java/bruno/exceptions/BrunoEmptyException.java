package bruno.exceptions;

/**
 * The BrunoEmptyException handles the case where the description for a new task is empty.
 */
public class BrunoEmptyException extends BrunoException {

    private String type;

    public BrunoEmptyException(String taskType) {
        super("Ruff Ruff! Description of " + taskType + " cannot be empty! ❌");
    }
}
