package Remy;

public class ChatbotException extends Exception {

    /**
     * Constructs new ChatbotException with an error message.
     * @param message The error message.
     */
    public ChatbotException(String message) {
        super(message);
    }

    /**
     * Returns String representation of the Exception.
     * @return String representation of the Exception.
     */
    @Override
    public String toString() {
        return this.getMessage();
    }
}
