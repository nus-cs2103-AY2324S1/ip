package chat.exceptions;

/**
 * @author juzzztinsoong
 */
public class InvalidCommandException extends ChatException {

    public InvalidCommandException() {
        super("I don't know what you meant!");
    }
}
