package bruno.exceptions;

public class BrunoEmptyException extends BrunoException {
    String type;

    public BrunoEmptyException(String type) {
        super("Ruff Ruff! Description of " + type + " cannot be empty! ❌");
    }
}
