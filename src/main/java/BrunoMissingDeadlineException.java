public class BrunoMissingDeadlineException extends BrunoException {
    BrunoMissingDeadlineException() {
        super("Ruff Ruff! You cannot add a Deadline task without setting the deadline! ❌");
    }
}
