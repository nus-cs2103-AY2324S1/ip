package duke.commands;

import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks in the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
        ui.showLine();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
