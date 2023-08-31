package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command to be executed by the chatbot. A <code>Command</code> object
 * corresponds to an executable command.
 */
public abstract class Command {
    private boolean isExit;

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
     * @param ui The user interface.
     * @param storage The storage.
     * @throws DukeException If an error occurs during execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
