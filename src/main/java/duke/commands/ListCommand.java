package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

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