package duke;

/**
 * Exception to be thrown when errors such as incorrect inputs entered by user occur.
 *
 * @author Qin Yan Er
 */
public class DukeException extends Exception {

    /**
     * Creates a new DukeException instance.
     *
     * @param message to be printed when errors occur.
     */
    public DukeException(String message) {
        super(message);
    }

}
