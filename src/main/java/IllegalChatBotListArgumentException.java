/**
 * Exception when a wrong task query string is supplied to ChatBotList.
 */
public class IllegalChatBotListArgumentException extends DukeException {
    public IllegalChatBotListArgumentException(String message) {
        super(message);
    }
    public IllegalChatBotListArgumentException() {
        super();
    }
}