package geraldbot.exception;

/**
 * Represents an exception thrown when an invalid command is provided by the user.
 */
public class DukeInvalidCommandException extends DukeException {

    /**
     * Constructs a DukeInvalidCommandException with a valid command but with invalid usage.
     *
     * @param command The command that caused the exception.
     */
    public DukeInvalidCommandException(String command) {
        super("☹ OOPS!!! The description of a " + command + " cannot be empty.");
    }

    /**
     * Constructs a DukeInvalidCommandException with a default error message for an unknown command.
     */
    public DukeInvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
