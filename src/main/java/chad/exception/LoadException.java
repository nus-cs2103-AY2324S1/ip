package chad.exception;

/**
 * Represents an error that occurred during the loading of local file.
 */
public class LoadException extends ChadException {
    public LoadException() {
        super("Oops! An error has occurred while loading the local data file");
    }
}
