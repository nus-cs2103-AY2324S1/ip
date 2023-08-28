package bruno.exceptions;

public class BrunoMissingEventException extends BrunoException {
    public BrunoMissingEventException() {
        super("Ruff Ruff! You cannot add an Event task without setting start and end time! ❌");
    }
}
