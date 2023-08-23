public class DukeIndexOutOfBoundsException extends DukeException {
    String activity;
    DukeIndexOutOfBoundsException(String activity) {
        super("Ruff Ruff! Task numbers to be " + activity + "ed cannot be greater than number of tasks in the list! ❌");
    }
}
