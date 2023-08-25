package exceptions;

public class InvalidCommandException extends HachiException {
    public InvalidCommandException(String cmd) {
        super(String.format("☹ OOPS!!! I'm sorry, but I don't know what \"%s\" means :-(", cmd));
    }
}