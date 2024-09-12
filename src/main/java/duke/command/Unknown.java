package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Represents a command for handling unknown or unsupported commands.
 * This command is used when the user enters a command that the application does not recognize.
 */
public class Unknown extends Command {

    /**
     * Constructs an Unknown command with the given input string.
     *
     * @param str The input string containing the unknown command.
     */
    public Unknown(String str) {
        super(str);
    }

    /**
     * Executes the Unknown command.
     * Throws a DukeException with the unknown command string to indicate an unsupported command.
     *
     * @param lst The task list (not used in this command).
     * @param io The user interface (not used in this command).
     * @param storage The storage handler (not used in this command).
     * @throws DukeException Always thrown with the unknown command string.
     */
    @Override
    public String execute(TaskList lst, UI io, Storage storage) throws DukeException {
        throw new DukeException(str);
    }
}
