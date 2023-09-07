package prts;

/**
 * Signals that the save file had failed to load, and so a new save had to be created.
 */
public class CreateNewSaveException extends PrtsException {

    /**
     * Constructs a CreateNewSaveException.
     */
    public CreateNewSaveException() {
        super("File failed to load, created new save file.");
    }

}
