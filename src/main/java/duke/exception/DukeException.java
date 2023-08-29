package duke.exception;

/**
 * An exception pertaining to Duke.
 */
public class DukeException extends RuntimeException{
    /**
     * Constructs a Duke Exception instance.
     *
     * @param s Message of the exception.
     */
    public DukeException(String s) {
        super(s);
    }
}
