package dukeExceptions;

public class LoadException extends DukeException {
    private int lineNumber;
    private String loadCommand;

    public LoadException(String message, int lineNumber, String loadCommand) {
        super(message);
        this.lineNumber = lineNumber;
        this.loadCommand = loadCommand;
    }

    @Override
    public String getMessage() {
        return this.lineNumber + ": " + super.getMessage() + " ---- " + this.loadCommand;
    }
}
