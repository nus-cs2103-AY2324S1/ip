package duke.exception;

/**
 * Class to handle duplicates exception
 *
 * @author marioalvaro
 */
public class DukeDuplicatesException extends DukeException {
    /**
     * Constructor fot the exception
     * @param e message of the error.
     */
    public DukeDuplicatesException(String e) {
        super("     ☹ OOPS!!! Duplicate Task Detected. \n");
    }
}
