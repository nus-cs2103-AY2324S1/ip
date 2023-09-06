package ipbot.util;

/**
 * Represents an exception in parsing or processing arguments given by the user.
 */
public class CommandArgumentException extends Exception{

    public CommandArgumentException(String message) {
        super(message);
    }
}
