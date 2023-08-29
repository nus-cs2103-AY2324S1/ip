/**
 * Exception when the task the user is attempting to access does not exist.
*/
public class NotInChatBotListException extends DukeException {
    public NotInChatBotListException(String message) {
        super(message);
    }
    public NotInChatBotListException() {
        super();
    }
}
