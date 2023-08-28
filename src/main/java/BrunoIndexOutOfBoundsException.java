public class BrunoIndexOutOfBoundsException extends BrunoException {
    String activity;
    BrunoIndexOutOfBoundsException(String activity) {
        super("Ruff Ruff! Task numbers to be " + activity + "ed cannot be greater than number of tasks in the list! ❌");
    }
}
