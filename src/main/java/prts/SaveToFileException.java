package prts;

/**
 * Signals that an error occurred attempting to save the state of the TaskList to the save file.
 */
public class SaveToFileException extends PrtsException {

    /**
     * Constructs a SaveToFileException.
     */
    public SaveToFileException() {
        super("Failed to save to save file.");
    }

}
