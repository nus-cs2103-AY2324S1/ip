package duke.exception;

/**
 * Handles invalid date.
 */
public class DukeInvalidDateFormatException extends DukeException {
    public String toString() {
        return super.toString() + "Invalid date format. Date format should be YYYY-MM-DD.";
    }
}
