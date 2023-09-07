package duke;

public class CreateNewSaveException extends DukeException {
    public CreateNewSaveException() {
        super("File failed to load, created new save file.");
    }
}
