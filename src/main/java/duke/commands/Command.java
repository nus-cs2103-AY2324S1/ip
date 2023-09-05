package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {
    /**
     * Executes the command
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage
     * @return
     * @throws DukeException if there is an error
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command is an exit command
     *
     * @return true if the command is an exit command
     */
    public abstract boolean isExit();
}
