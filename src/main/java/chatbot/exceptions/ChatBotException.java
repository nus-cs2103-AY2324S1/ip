package chatbot.exceptions;

public class ChatBotException extends Exception {
    @Override
    public String toString() {
        return "OOPS, an error has occurred within me... \nDon't worry and just try again, human...";
    }
}
