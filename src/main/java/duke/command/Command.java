package duke.command;

import duke.exception.DukeException;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to be executed by the chatbot. A <code>Command</code> object
 * corresponds to an executable command.
 */
public abstract class Command {
    private final boolean isExit;

    /**
     * Constructs a command.
     *
     * @param isExit Whether the command is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return Whether the command is an exit command.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command.
     *
     * @param taskList The task list.
     * @param ui       The user interface.
     * @param storage  The storage.
     * @return the response to the user.
     * @throws DukeException If an error occurs during execution.
     */
    public abstract Response execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
