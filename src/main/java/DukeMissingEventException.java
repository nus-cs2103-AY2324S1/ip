public class DukeMissingEventException extends DukeException{
    DukeMissingEventException() {
        super("Ruff Ruff! You cannot add an Event task without setting start and end time! ❌");
    }
}
