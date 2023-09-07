package duke;

public class SaveToFileException extends DukeException {
    public SaveToFileException() {
        super("Failed to save to save file.");
    }
}
