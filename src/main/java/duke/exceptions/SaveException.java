package duke.exceptions;

/**
 * Encasuplates an error saving the state to disk.
 */
public class SaveException extends DukeException {
    public SaveException() {
        super("Error saving file!");
    }
}
