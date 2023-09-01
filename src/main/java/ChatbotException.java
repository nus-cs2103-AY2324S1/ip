public class ChatbotException extends Exception {
    public ChatbotException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
