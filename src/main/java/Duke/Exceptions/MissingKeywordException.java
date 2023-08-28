package Duke.Exceptions;

public class MissingKeywordException extends Exception {
    public MissingKeywordException(String message) {
        super("MissingKeywordException: " + message + "\n");
    }
}