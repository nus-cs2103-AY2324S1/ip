package duke.exceptions;

/**
 * Date and time parse exception for Duke.
 */
public class DukeDateTimeParseException extends DukeException {
    public DukeDateTimeParseException() {
        super("OOPS!!! Date and Time format does not match dd mmm yyyy - hh:mm");
    }
}
