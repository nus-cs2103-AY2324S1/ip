/**
 * Exception when the task the user is attempting to access does not exist.
*/
public class NotInChatBotListException extends ChatBotListException {
    public NotInChatBotListException(String message) {
        super(message);
    }
    public NotInChatBotListException() {
        super("Make sure your item is in the list!\n"
                + "You may check using the \"list\" command.");
    }
}
