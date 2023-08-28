package bruno.exceptions;

public class BrunoIntegerMismatchException extends BrunoException {
    String activity;

    public BrunoIntegerMismatchException(String activity) {
        super("Ruff Ruff! Tasks to be " + activity + "ed can only be integers! ❌");
    }
}
