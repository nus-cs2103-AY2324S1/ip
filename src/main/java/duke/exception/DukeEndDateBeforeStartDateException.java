package duke.exception;

/**
 * Represents an error when Duke encounters invalid start and end date for Event task.
 */
public class DukeEndDateBeforeStartDateException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + "Your start date cannot be after your end date :-(\n";
    }
}
