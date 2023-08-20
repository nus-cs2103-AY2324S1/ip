package errors;

public class IncorrectMarkParametersException extends IllegalArgumentException {
    public IncorrectMarkParametersException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
