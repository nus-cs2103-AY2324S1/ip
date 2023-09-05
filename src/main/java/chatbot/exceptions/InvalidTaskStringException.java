package chatbot.exceptions;

public class InvalidTaskStringException extends ChatBotException {
    @Override
    public String toString() {
        return "\tInvalid String representation of task";
    }
}
