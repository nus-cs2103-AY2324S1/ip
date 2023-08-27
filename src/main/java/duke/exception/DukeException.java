package duke.exception;

/**
 * An exception pertaining to duke.Duke.
 */
public class DukeException extends RuntimeException{

    /**
     * Creates a duke.Duke Exception instance.
     *
     * @param s Message of the exception.
     */
    public DukeException(String s) {
        super(s);
    }
}
