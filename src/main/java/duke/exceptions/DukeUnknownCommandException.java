package duke.exceptions;

/**
 * Represents an exception where the command is unknown
 *
 * @author Andrew Daniel Janong
 */
public class DukeUnknownCommandException extends DukeException {
    public DukeUnknownCommandException(String message) {
        super("OOPS!!! Sorry, but I don't think \"" + message + "\" is a valid command.");
    }
}
