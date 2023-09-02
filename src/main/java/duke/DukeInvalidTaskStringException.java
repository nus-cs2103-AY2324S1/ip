package duke;

/**
 * Represents a Duke exception when task file contains an unreadable task.
 */
public class DukeInvalidTaskStringException extends DukeException {

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return "Error: Unable to read task in text file!";
    }
}
