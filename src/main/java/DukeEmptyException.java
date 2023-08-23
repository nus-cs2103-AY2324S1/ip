public class DukeEmptyException extends DukeException {
    String type;
    DukeEmptyException(String type) {
        super("Ruff Ruff! Description of " + type + " cannot be empty! ❌");
    }
}
