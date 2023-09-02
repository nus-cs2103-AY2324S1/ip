package exceptions;

public class GBotException extends RuntimeException {
    public GBotException() {
        super("Sorry. I don't understand the command");
    }

    public GBotException(String message) {
        super(message);
    }
}
