package bot.exception;
import java.lang.Exception;

public class BotException extends Exception {
    public static final String SPACER = "____________________________________________________________";

    /**
     * Constructs a new BotException with the specified detail message.
     *
     * @param errorMessage the detail message.
     */
    public BotException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns the String representation of the BotException object
     *
     * @return String representation of the BotException object
     */
    @Override
    public String toString() {
        return BotException.SPACER + "\n" +
                super.getMessage() + "\n" +
                BotException.SPACER;
    }
}
