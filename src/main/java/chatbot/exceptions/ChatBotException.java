package chatbot.exceptions;

/**
 * Class that represents any generic exception associated with the ChatBot's logic.
 */
public class ChatBotException extends Exception {
    @Override
    public String toString() {
        return "OOPS, an error has occurred within me... \nDon't worry and just try again, human...";
    }
}
