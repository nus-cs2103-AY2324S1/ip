public class DukeNegativeArgException extends DukeException {
    String activity;
    DukeNegativeArgException(String activity) {
        super("Ruff Ruff! Task numbers to be " + activity + "ed cannot be negative! ❌");
    }
}
