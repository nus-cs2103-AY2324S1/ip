public class TimeParsingException extends ChatException {
    public TimeParsingException(String message) {
        super("I'm sorry, I don't understand the time you entered: " + message + "\n");
    }
}
