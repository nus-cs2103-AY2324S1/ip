package duke;

/**
 * Used to handle exceptions specific to Duke.
 *
 * @author Teo Kai Sheng
 */
public class DukeException extends Exception {
    /**
     * Constructor for creating a DukeException.
     *
     * @param e Error message.
     */
    public DukeException(String e) {
        super(e);
    }
}
