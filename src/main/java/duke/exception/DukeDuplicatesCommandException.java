package duke.exception;

/**
 * Class to handle duplicates mode exception.
 *
 * @author marioalvaro.
 */
public class DukeDuplicatesCommandException extends DukeException {
    /**
     * Constructor of the exception
     * @param e the error in string.
     */
    public DukeDuplicatesCommandException(String e) {
        super("     ☹ OOPS!!! Duplicate Command is incorrect. \n");
    }

}
