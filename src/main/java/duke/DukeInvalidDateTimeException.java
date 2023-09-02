package duke;

/**
 * Represents a Duke exception when a wrongly formatted date and time is entered.
 */
public class DukeInvalidDateTimeException extends DukeException {

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return "Error: Enter valid date and time in the format DD/MM/YYYY HH:MM.";
    }
}
