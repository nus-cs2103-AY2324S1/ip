package hachi.exceptions;

/**
 * Exception thrown when an invalid command is given by the user.
 */
public class InvalidCommandException extends HachiException {
    public InvalidCommandException(String cmd) {
        super(String.format("☹ OOPS!!! I'm sorry, but I don't know what \"%s\" means :-(", cmd));
    }
}
