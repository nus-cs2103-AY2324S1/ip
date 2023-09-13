package exceptions;

public class SavedDataFormatException extends RuntimeException {
    public SavedDataFormatException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "The saved data is not properly formatted.";
    }
}
