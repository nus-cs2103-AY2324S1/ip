package bruno.exceptions;

public class BrunoMissingDeadlineException extends BrunoException {
    public BrunoMissingDeadlineException() {
        super("Ruff Ruff! You cannot add a Deadline task without setting the deadline! ❌");
    }
}
