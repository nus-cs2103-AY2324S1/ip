public class InvalidCommandException extends DidierException {

    public InvalidCommandException(String command) {
        super("I don't quite understand " + command + ". ");
    }
}
