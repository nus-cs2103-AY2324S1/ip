public class NoDescriptionException extends IncorrectInputException {
    public NoDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Please ensure that you key in your arguments correctly.";
    }
}
