package bot.exception;

public class IncompleteBotException extends BotException {

    /**
     * Constructs a new IncompleteBotException with the specified detail message.
     *
     * @param errorMessage the detail message.
     */
    public IncompleteBotException(String errorMessage) {
        super(errorMessage);
    }
}
