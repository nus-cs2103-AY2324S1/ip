package exceptions;

public class UnknownCommandException extends Exception{
    public UnknownCommandException(String message) {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n\t You typed: " + message);
    }
}
