package skye.commands;

import skye.data.TaskList;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents the command to list all tasks.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Executes the list command by retrieving a list of tasks from the
     * TaskList and displaying it on the UI.
     *
     * @param taskList TaskList
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showTasks(taskList.getTasks());
    }
}