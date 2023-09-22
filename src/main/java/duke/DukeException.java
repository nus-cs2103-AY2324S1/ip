package duke;

/**
 * DukeException is a custom Exception made for the Duke Application
 */
public class DukeException extends Exception{
    /**
     * Constructs a DukeException that is an extension of the Exception Class.
     *
     * @param str The error message that causes this Exception.
     */
    public DukeException (String str) {
        super(str);
    }
}
