package error;

public final class ParseException extends RuntimeException {
    private final String line;

    public ParseException(String line, String message) {
        super(message);
        this.line = line;
    }

    public String getLine() {
        return line;
    }
}
