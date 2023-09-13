package dukeexceptions;

/**
 * This Exception class is for when an IOException happens when saving data.
 */
public class DukeSaveException extends DukeException {
    public DukeSaveException(String message) {
        super(message);
    }
}
