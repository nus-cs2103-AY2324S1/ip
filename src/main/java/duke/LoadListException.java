package duke;
/**
 * Exception when the ChatBotList is unable to load list.
 */
public class LoadListException extends ChatBotListException {
    public LoadListException(String message) {
        super(message);
    }
    public LoadListException() {
        super();
    }
}
