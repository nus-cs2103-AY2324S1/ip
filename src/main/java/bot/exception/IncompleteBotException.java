package bot.exception;

public class IncompleteBotException extends BotException {
    public IncompleteBotException(String errorMessage) {
        super(errorMessage);
    }
}
