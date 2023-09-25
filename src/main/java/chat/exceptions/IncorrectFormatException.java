package chat.exceptions;

/**
 * @author juzzztinsoong
 */
public class IncorrectFormatException extends ChatException {
    public IncorrectFormatException() {
        super("You used the wrong format!");
    }
}
