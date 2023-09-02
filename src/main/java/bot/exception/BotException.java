package bot.exception;
import java.lang.Exception;

public class BotException extends Exception {
    public static final String SPACER = "____________________________________________________________";

    public BotException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return BotException.SPACER + "\n" +
                super.getMessage() + "\n" +
                BotException.SPACER;
    }
}
