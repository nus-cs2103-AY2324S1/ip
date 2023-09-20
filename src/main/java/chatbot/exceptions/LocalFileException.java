package chatbot.exceptions;

/**
 * Class that represents a generic exception with a local data file.
 */
public class LocalFileException extends ChatBotException {
    private String filePath;

    public LocalFileException(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public String toString() {
        return "OOPS, an error occurred with file " + this.filePath;
    }
}
