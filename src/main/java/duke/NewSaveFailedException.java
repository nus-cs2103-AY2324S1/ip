package duke;

public class NewSaveFailedException extends DukeException {
    public NewSaveFailedException() {
        super("Failed to load save file, new save file could not be created.");
    }
}
