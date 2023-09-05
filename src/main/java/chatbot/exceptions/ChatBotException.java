package chatbot.exceptions;

public class ChatBotException extends Exception {
    @Override
    public String toString() {
        return "\tOOPS, an error has occurred within me... Don't worry and just try again, human...";
    }
}
