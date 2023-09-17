package banterbot.exception;

/**
 * Represents an Exception where a BanterBot.command is used in the wrong context.
 */
public class WrongUseOfCommandException extends DukeException {
    public WrongUseOfCommandException() {
        super("No extra text after this BanterBot.command bro..");
    }
}
