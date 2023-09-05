package chatbot.exceptions;

public class IllegalCommandException extends ChatBotException {
    @Override
    public String toString() {
        return "\tSorry, I'm not sure what that means...";
    }
}
