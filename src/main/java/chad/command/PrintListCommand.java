package chad.command;

import chad.util.Storage;
import chad.util.TaskList;
import chad.util.Ui;

/**
 * Represents a Print List command to be executed.
 */
public class PrintListCommand extends Command {

    /**
     * Executes the list of commands to print the TaskList.
     *
     * @param taskList The given TaskList to be printed.
     * @param ui The given Ui to print the TaskList.
     * @param storage The given Storage that saves the TaskList locally.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.addTaskList(taskList);
    }
}
