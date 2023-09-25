package chatbot.exceptions;

/**
 * Exception that is thrown when the save file is not found.
 */
public class SaveFileNotFound extends ChatBotException {

    public SaveFileNotFound(String e) {
        super(e);
    }

}
