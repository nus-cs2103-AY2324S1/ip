package errors;

public class DotException extends RuntimeException {
    public DotException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
