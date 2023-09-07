package duke.exception;

public class DukeDateOutOfRange extends DukeException {
    public DukeDateOutOfRange() {
        super("Either the given date is not applicable" +
                " or the time is not given in am/pm format.");
    }
}
