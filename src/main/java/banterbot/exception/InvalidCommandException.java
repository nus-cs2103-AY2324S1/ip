package banterbot.exception;

/**
 * Represents an Exception where an invalid BanterBot.command was given.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String command) {
        super("No such BanterBot.command bruh: " + command);
    }
}
