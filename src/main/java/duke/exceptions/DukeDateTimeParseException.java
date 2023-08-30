package duke.exceptions;

public class DukeDateTimeParseException extends DukeException {
    public DukeDateTimeParseException() {
        super("OOPS!!! Date and Time format does not match dd mmm yyyy - hh:mm");
    }
}
