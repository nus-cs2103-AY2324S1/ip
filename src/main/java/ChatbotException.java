public class ChatbotException extends Exception {
    public ChatbotException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Sucks to be you, but " + this.getMessage();
    }
}
