package exceptions;

public class InvalidKeywordException extends RuntimeException {
    public InvalidKeywordException() {
        super("Sorry I do not understand you, can you provide another keyword or I get you some fries?");
    }
}
