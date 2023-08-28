public class BrunoNegativeArgException extends BrunoException {
    String activity;

    BrunoNegativeArgException(String activity) {
        super("Ruff Ruff! Task numbers to be " + activity + "ed cannot be negative! ❌");
    }
}
