package prts.command;

import prts.Storage;
import prts.TaskList;
import prts.Ui;

/**
 * The command for listing the current state of the TaskList.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * @param tasks The list of tasks currently stored.
     * @param ui The UI object stored by PRTS.
     * @param storage The Storage object stored by PRTS.
     * @return The string to be displayed to the user upon successful execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(tasks);
    }

}
