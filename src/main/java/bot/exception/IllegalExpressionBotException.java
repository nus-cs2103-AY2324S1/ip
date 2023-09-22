package bot.exception;

public class IllegalExpressionBotException extends BotException {

    /**
     * Constructs a new IllegalExpressionBotException with the specified detail message.
     *
     * @param errorMessage the detail message.
     */
    public IllegalExpressionBotException(String errorMessage) {
        super(errorMessage);
    }
}
