package mainDuke.exceptions;

/**
 * Represents a Duke exclusive error, not including parsing between String and Task
 */
public class DukeException extends Exception {
    /**
     * public onstructor, returns a <code>DukeException</code> instance
     * @param message message that is to be displayed
     */
    public DukeException(String message) {
        super(message);
    }
}
