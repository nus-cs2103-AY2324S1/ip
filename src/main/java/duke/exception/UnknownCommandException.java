package duke.exception;

public class UnknownCommandException extends Exception{
    public UnknownCommandException() {
        super("What gibberish are you saying man?!\n");
    }
}
