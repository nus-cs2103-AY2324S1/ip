public class DukeMissingDeadlineException extends DukeException {
    DukeMissingDeadlineException() {
        super("Ruff Ruff! You cannot add a Deadline task without setting the deadline! ❌");
    }
}
