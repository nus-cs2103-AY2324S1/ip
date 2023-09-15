package duke.exception;

/**
 * The UnknownCommandException extends DukeException and is used
 * to denote that duke.Duke does not understand that particular command
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I do not understand this command");
    }
}
