package duke.exceptions;

/**
 * The <code>InvalidFindException</code> class represents an exception that is
 * thrown when an invalid "find" command is encountered in Duke's input. This exception
 * is typically thrown when the "find" command does not include a valid keyword or key phrase.
 * The correct format for the "find" command is "find (keyword / key phrases)".
 *
 * @author [Your Name]
 * @version [Version Number]
 */
public class InvalidFindException extends DukeException {

    /**
     * Constructs an <code>InvalidFindException</code> with a default error message.
     * The default error message instructs the user on the correct usage of the "find" command.
     */
    public InvalidFindException() {
        super("Invalid find command. Enter 'find (keyword / key phrases)'");
    }
}
