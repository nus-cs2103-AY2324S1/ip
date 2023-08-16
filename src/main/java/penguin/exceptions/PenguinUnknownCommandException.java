package penguin.exceptions;

public class PenguinUnknownCommandException extends PenguinException{
    public PenguinUnknownCommandException() {
        super("My bird brain can't understand that!");
    }
}
