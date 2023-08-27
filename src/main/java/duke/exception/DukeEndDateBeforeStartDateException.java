package duke.exception;

public class DukeEndDateBeforeStartDateException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Your start date cannot be after your end date :-(\n";
    }
}
