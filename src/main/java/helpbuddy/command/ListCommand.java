package helpbuddy.command;

import helpbuddy.storage.Storage;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

/**
 * The ListCommand class calls Ui to list all Task in TaskList upon execution.
 */
public class ListCommand extends Command {

    /**
     * Calls Ui to list all Task in TaskList upon execution.
     * @param taskList the tasklist for Task to be listed.
     * @param ui the ui that prints message.
     * @param storage the storage with saved data in TaskList.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printListMessage(taskList);
    }
}
