package chatbot.exceptions;

public class LocalFileException extends ChatBotException {
    private String filePath;

    public LocalFileException(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public String toString() {
        return "\tOOPS, an error occurred with file " + this.filePath;
    }
}
