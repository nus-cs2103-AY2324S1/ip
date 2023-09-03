package skye.commands;

import skye.data.TaskList;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents an invalid command that the user has entered.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command in which this case a help message
     * is shown by the UI.
     *
     * @param taskList TaskList
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showInvalidCommandMsg();
    }
}