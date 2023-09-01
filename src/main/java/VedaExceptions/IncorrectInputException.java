package VedaExceptions;

public class IncorrectInputException extends RuntimeException {
    public IncorrectInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Please ensure that you key in your arguments correctly.";
    }
}
