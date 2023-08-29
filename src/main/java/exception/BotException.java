package exception;
import java.lang.Exception;

public class BotException extends Exception {

    public BotException(String errorMessage) {
        super(errorMessage);
    }
}
