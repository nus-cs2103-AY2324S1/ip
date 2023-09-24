package ekud.error;

/**
 * Represents an error with parsing a command.
 */
public final class ParseException extends EkudException {
    private final String line;

    /**
     * Creates a new parse exception.
     * 
     * @param line    The text that caused the parsing error.
     * @param message The message to display to the user.
     */
    public ParseException(String line, String message) {
        super(message);
        this.line = line;
    }

    /**
     * Returns the text that caused the parsing error.
     * 
     * @return The text.
     */
    public String getLine() {
        return line;
    }
}
