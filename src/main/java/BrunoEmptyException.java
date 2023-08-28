public class BrunoEmptyException extends BrunoException {
    String type;
    BrunoEmptyException(String type) {
        super("Ruff Ruff! Description of " + type + " cannot be empty! ❌");
    }
}
