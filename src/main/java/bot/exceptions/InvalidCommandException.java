package bot.exceptions;

/**
 * Exception for when an invalid command is sent to the bot.
 */
public class InvalidCommandException extends BotException {
    /**
     * Default constructor. Currently set to Trash Gremlin Caelus personality.
     */
    public InvalidCommandException() {
        super("Sorry, I dunno what you're saying. Must be the trash getting to my brain.");
    }
}
