package duke;
/**
 * Encapsulates an exception thrown by the ChatBotList
 */
public class ChatBotListException extends DukeException{
    public ChatBotListException(String message) {
        super(message);
    }
    public ChatBotListException() {
        super();
    }
}
