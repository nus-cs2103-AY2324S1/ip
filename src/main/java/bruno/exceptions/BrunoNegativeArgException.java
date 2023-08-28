package bruno.exceptions;

public class BrunoNegativeArgException extends BrunoException {
    String activity;

    public BrunoNegativeArgException(String activity) {
        super("Ruff Ruff! Task numbers to be " + activity + "ed cannot be negative! ❌");
    }
}
