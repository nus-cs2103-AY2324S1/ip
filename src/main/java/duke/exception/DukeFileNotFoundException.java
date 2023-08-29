package duke.exception;

/**
 * An exception thrown when the file is not found.
 */
public class DukeFileNotFoundException extends DukeException {
    /**
     * Constructs a DukeFileNotFoundException instance.
     */
    public DukeFileNotFoundException() {
        super("No available file");
    }
}
