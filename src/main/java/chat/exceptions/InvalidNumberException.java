package chat.exceptions;

/**
 * @author juzzztinsoong
 */
public class InvalidNumberException extends ChatException {

    public InvalidNumberException() {
        super("You didn't specify the task number!");
    }
}
