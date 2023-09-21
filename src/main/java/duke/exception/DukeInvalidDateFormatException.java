package duke.exception;

/**
 * Represents an error when Duke encounters invalid date format.
 */
public class DukeInvalidDateFormatException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + "Please enter a date of format yyyy-mm-dd eg: 2023-09-26 :-(\n";
    }
}
