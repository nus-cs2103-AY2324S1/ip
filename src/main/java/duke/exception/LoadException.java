package duke.exception;

/**
 * Represents an error that occurred during the loading of local file.
 */
public class LoadException extends DukeException {

    public LoadException() {
        super("Oops! An error has occurred while loading the local data file");
    }
}
