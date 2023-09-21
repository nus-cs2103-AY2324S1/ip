package bot.exceptions;

/**
 * Exception for when the storage class fails to load or save data for any reason.
 */
public class LoadingException extends BotException {
    /**
     * Creates a LoadingException with the given string as the error message.
     *
     * @param str Error message.
     */
    public LoadingException(String str) {
        super(str);
    }
}
