package duke.exceptions;

public class CommandNotRecognizedException extends ChatException {
    public CommandNotRecognizedException(String message) {
        super(message);
    }
}
