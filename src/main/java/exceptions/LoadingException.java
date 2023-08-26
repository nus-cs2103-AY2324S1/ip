package exceptions;

/**
 * Exception for when the storage class fails to load or save data for any reason.
 */
public class LoadingException extends BotException {
    public LoadingException(String str) {
        super(str);
    }
}
