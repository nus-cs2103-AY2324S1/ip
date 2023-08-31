package bruno.exceptions;

/**
 * The BrunoMissingEventException handles the case where an Event task is added without either the start or
 * the end time.
 */
public class BrunoMissingEventException extends BrunoException {

    public BrunoMissingEventException() {
        super("Ruff Ruff! You cannot add an Event task without setting start and end time! ❌");
    }
}
