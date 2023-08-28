public class BrunoMissingEventException extends BrunoException {
    BrunoMissingEventException() {
        super("Ruff Ruff! You cannot add an Event task without setting start and end time! ❌");
    }
}
