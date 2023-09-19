package cheems.exceptions;

/**
 * Custom exception class to represent an invalid keyword input.
 * This exception is thrown when an input keyword is not recognised by the chatbot.
 */
public class InvalidKeywordException extends RuntimeException {
    /**
     * Creates a new runtime exception due to keyword not supported by the current version of bot.
     */
    public InvalidKeywordException() {
        super("Sorry I do not understand you, can you provide another keyword or I get you some fries?");
    }
}
