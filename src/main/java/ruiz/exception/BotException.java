package ruiz.exception;

/**
 * Exceptions that may arise when the chatbot is in operation
 */
public class BotException extends Exception {
    /**
     * A constructor for a BotException
     * @param errorMessage message to be displayed when the error is thrown.
     */
    public BotException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * This method coverts the value of the BotException into a String type.
     * @return a String representation of the Exception with its error message.
     */
    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                super.getMessage() +
                "\n____________________________________________________________\n";
    }
}
