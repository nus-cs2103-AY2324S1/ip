package exceptions;

/**
 * SavedDataFormatException extends RuntimeException and is used when the saved data is not properly formatted.
 *
 * @author Sebastian Tay
 */
public class SavedDataFormatException extends RuntimeException {
    public SavedDataFormatException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "The saved data is not properly formatted.";
    }
}
