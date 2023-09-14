package exceptions;

public class UpdateDataException extends RuntimeException {
    public UpdateDataException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
