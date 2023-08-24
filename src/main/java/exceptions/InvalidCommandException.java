package exceptions;

public class InvalidCommandException extends ThorndikeException {

    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }

}
