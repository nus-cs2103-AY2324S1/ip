/**
 * Exception when a wrong task query string is supplied to ChatBotList.
 */
public class IllegalChatBotListArgumentException extends ChatBotListException {
    public IllegalChatBotListArgumentException(String message) {
        super(message);
    }
    public IllegalChatBotListArgumentException() {
        super();
    }
}