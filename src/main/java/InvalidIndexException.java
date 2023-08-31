/**
 * Exception thrown when the index given for mark, unmark, or delete is wrong.
 * 
 * @author Owen Yeo
 */
public class InvalidIndexException extends ChatBotException {

    public InvalidIndexException(String e) {
        super(e);
    }
}
