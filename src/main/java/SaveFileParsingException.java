public class SaveFileParsingException extends DukeException {
    private static final String ERROR_MESSAGE = "I couldn't understand the save file...";
    public SaveFileParsingException() {
        super(ERROR_MESSAGE);
    }
}
