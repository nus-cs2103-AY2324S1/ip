package devybot.exceptions;

/**
 * Signals that an exceptional condition has occurred in the DevyBot
 * application.
 */
public class DevyBotException extends Exception {

    /**
     * Constructs a new DevyBotException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the
     *                getMessage() method)
     */
    public DevyBotException(String message) {
        super(message);
    }
}
