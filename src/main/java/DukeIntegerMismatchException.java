public class DukeIntegerMismatchException extends DukeException {
    String activity;
    DukeIntegerMismatchException(String activity) {
        super("Ruff Ruff! Tasks to be " + activity + "ed can only be integers! ❌");
    }
}
