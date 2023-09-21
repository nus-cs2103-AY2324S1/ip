package chad.exception;

/**
 * Represents an error that occurred during the loading of local file.
 */
public class SaveException extends ChadException {

    public SaveException() {
        super("Oops! An error has occurred while saving the local data file");
    }
}