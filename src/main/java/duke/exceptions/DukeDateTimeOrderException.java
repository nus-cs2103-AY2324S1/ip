package duke.exceptions;

/**
 * Date and time order exception for Duke.
 */
public class DukeDateTimeOrderException extends DukeException {
    public DukeDateTimeOrderException() {
        super("OOPS!!! 'From' date and time should come before 'to' date and time");
    }
}
