package crusader.exception;

/**
 * Represents an error from wrong time formatting in the bot.
 */
public class CrusaderDateFormatException extends CrusaderParseException {
    public CrusaderDateFormatException() {
        super("I only understand dates in the format dd/mm/yyyy hh(24 hours)!");
    }
}
