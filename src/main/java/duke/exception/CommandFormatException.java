package duke.exception;

/**
 * Exception thrown when the general format of a command is wrong.
 */
public class CommandFormatException extends DukeException {

    /**
     * Constructor for CommandFormatException.
     *
     * @param cmd The command in question.
     * @param format The correct format for the command.
     */
    public CommandFormatException(String cmd, String format) {
        super(String.format("☹ OOPS!!! %s command should have the following format:\n  %s", cmd, format));
    }

}
