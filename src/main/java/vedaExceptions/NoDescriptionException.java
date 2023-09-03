package vedaExceptions;

public class NoDescriptionException extends IncorrectInputException {
    public NoDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
