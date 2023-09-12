package cringebot.exceptions;

/**
 * Class to handle exceptions thrown by Duke.
 */
public class CringeBotException extends Exception {
    /**
     * Throws an exception for the Duke program.
     *
     * @param message information about the exception.
     */
    public CringeBotException(String message) {
        super(message);
    }
}