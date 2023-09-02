package duke;

/**
 * Represents a Duke exception when a command is entered without necessary argument(s).
 */
public class DukeMissingArgumentException extends DukeException {

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("Error: Command is missing argument(s)!");
    }

}
