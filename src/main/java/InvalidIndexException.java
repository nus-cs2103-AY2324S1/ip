public class InvalidIndexException extends BotException {
    public InvalidIndexException() {
        super("Sorry, that index doesn't exist. Please key in a valid index.");
    }
}
