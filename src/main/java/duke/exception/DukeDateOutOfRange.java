package duke.exception;

/**
 * An exception thrown when the given date is
 * out of the possible range.
 */
public class DukeDateOutOfRange extends DukeException {
    /**
     * Constructor for the exception.
     */
    public DukeDateOutOfRange() {
        super("Either the given date is not applicable"
                + " or the time is not given in am/pm format.");
    }
}
