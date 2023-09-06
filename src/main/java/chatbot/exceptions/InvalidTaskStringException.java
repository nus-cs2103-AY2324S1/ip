package chatbot.exceptions;

public class InvalidTaskStringException extends ChatBotException {
    @Override
    public String toString() {
        return "Invalid String representation of task";
    }
}
