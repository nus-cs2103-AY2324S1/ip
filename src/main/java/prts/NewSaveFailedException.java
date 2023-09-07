package prts;

/**
 * Signals that the save file failed to load, and furthermore, that PRTS attempted to create a new
 * save file, but failed.
 */
public class NewSaveFailedException extends PrtsException {

    /**
     * Constructs a NewSaveFailedException.
     */
    public NewSaveFailedException() {
        super("Failed to load save file, new save file could not be created.");
    }
}
