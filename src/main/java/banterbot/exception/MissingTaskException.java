package banterbot.exception;

/**
 * Represents an Exception where a BanterBot.task is missing when expected.
 */
public class MissingTaskException extends DukeException {
    public MissingTaskException(String command) {
        super(command + " needs a BanterBot.task after it...");
    }
}
