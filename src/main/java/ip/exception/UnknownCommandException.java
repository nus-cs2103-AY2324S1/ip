package Exception;

public class UnknownCommandException extends Exception{
    public UnknownCommandException() {
        super("What gibberish are you saying man?!");
    }
}
