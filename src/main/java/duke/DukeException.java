package duke;

/**
 * Parent class of all the checked duke.exceptions to be implemented for duke.Duke
 */
public class DukeException extends Exception {

    /**
     * Class constructor containing the message of the exception
     * @param message the string message associated with the exception
     */
    public DukeException(String message) {
        super(message);
    }
}
