package dukeExceptions;

/**
 * This Exception class is for when duke met a corrupted file while loading.
 */
public class DukeLoadException extends DukeException {
    private int lineNumber;
    private String loadCommand;

    /**
     * Constructs a DukeLoadException object.
     * @param message The string to print out as the message for the error.
     * @param lineNumber The line number of the erroneous string that was loaded from the save file.
     * @param loadCommand The erroneous string that was loaded from the save file.
     */
    public DukeLoadException(String message, int lineNumber, String loadCommand) {
        super(message);
        this.lineNumber = lineNumber;
        this.loadCommand = loadCommand;
    }

    /**
     * Returns a custom message to state the lineNumber, error message and the corrupted line.
     * @return Returns a string that states the lineNumber, error message and the corrupted line.
     */
    @Override
    public String getMessage() {
        return this.lineNumber + ": " + super.getMessage() + " ---- " + this.loadCommand;
    }
}
