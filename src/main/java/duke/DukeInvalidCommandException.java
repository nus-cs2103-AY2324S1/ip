package duke;

/**
 * Represents a Duke exception when an invalid command is entered.
 */
public class DukeInvalidCommandException extends DukeException {
    String command;

    /**
     * Constructor for the exception.
     *
     * @param command Invalid command entered.
     */
    public DukeInvalidCommandException(String command) {
        this.command = command;
    }

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("Error: %s is an invalid command!", this.command);
    }

}
