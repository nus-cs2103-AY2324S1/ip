package bot.exception;

public class FileErrorBotException extends BotException {

    /**
     * Constructs a new FileErrorBotException with the specified detail message.
     *
     * @param errorMessage the detail message.
     */
    public FileErrorBotException(String errorMessage) {
        super(errorMessage);
    }
}
