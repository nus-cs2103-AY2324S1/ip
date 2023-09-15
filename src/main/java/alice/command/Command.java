package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user and to be executed.
 */
public class Command {
    public static final String INDEX_NOT_NUMBER_ERROR_MESSAGE = "OOPS!!! The index of a task must be a number.";
    public static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "OOPS!!! I don't know what that means :-(";
    protected boolean isExit = false; // Whether the command is an exit command.

    /**
     * Executes the command for the given list of tasks, user interface and storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return The response to the command.
     * @throws DukeException If an error occurs during execution.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(UNKNOWN_COMMAND_ERROR_MESSAGE);
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Different keywords for commands.
     */
    public enum CommandKeyword {
        LIST, MARK, UNMARK, DELETE, BYE, TODO, DEADLINE, EVENT, FIND, TAG, UNTAG
    }
}
