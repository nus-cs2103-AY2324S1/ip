package chatterbot.exceptions;

/**
 * This is the superclass for all exceptions thrown while using ChatterBot.
 */
public class ChatterBotException extends Exception {
    public ChatterBotException(String message) {
        super(message);
    }
}
