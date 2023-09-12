package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list all tasks
 */
public class ListCommand extends Command {
    /**
     * Executes the command
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage
     * @return the message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks.getLength()) + "\n" + tasks.showList();
    }

    /**
     * Returns true if the command is an exit command
     *
     * @return true if the command is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
