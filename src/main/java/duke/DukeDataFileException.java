package duke;

/**
 * Represents a Duke exception when any error occurs related to the data file.
 */
public class DukeDataFileException extends DukeException {

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("Error: Data file.");
    }

}
