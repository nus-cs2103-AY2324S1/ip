package bruno.exceptions;

/**
 * The BrunoMissingDeadlineException handles the case where the user enters a new Deadline task without a
 * deadline.
 */
public class BrunoMissingDeadlineException extends BrunoException {

    public BrunoMissingDeadlineException() {
        super("Ruff Ruff! You cannot add a Deadline task without setting the deadline! ❌");
    }
}
