package chat.exceptions;

/**
 * @author juzzztinsoong
 */
public class NoSuchEntryException extends ChatException {
    public NoSuchEntryException() {
        super("There is no such entry!");
    }
}
