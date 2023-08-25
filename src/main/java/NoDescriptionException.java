public class NoDescriptionException extends RuntimeException {
    public NoDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Please ensure that you key in your arguments correctly.";
    }
}
