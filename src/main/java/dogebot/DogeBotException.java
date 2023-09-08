package dogebot;

/**
 * Custom exception class to throw custom error messages.
 *
 * @author Kenvyn Kwek
 */
public class DogeBotException extends Exception {
    /**
     * Initializes DogeBotException object with input exception message.
     * @param message Exception message.
     */
    public DogeBotException(String message) {
        super(message);
    }
}
