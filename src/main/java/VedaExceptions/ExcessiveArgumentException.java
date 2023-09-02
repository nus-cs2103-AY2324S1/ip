package VedaExceptions;

public class ExcessiveArgumentException extends IncorrectInputException {
    public ExcessiveArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
