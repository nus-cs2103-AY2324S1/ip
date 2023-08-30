package ekud.error;

public final class ParseException extends EkudException {
    private final String line;

    public ParseException(String line, String message) {
        super(message);
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
