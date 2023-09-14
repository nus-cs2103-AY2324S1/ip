package exceptions;

/**
 * UpdateDataException extends RunTimeException and is thrown when there is an issue in updating the saved data.
 *
 * @author Sebastian Tay
 */
public class UpdateDataException extends RuntimeException {
    public UpdateDataException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
