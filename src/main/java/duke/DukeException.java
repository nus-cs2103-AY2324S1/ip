package duke;

/**
 * Represents exceptions specific to duke.Duke.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a duke.DukeException instance.
     *
     * @param errorMessage Error message to be displayed.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
