package skye.commands;

import skye.data.TaskList;
import skye.data.VenueList;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents the command to list all tasks.
 */
public class ListTasksCommand extends ListCommand {
    public static final String RESOURCE = "tasks";

    /**
     * Executes the list command by retrieving a list of tasks from the
     * TaskList and displaying it on the UI.
     *
     * @param taskList TaskList
     * @param venueList VenueList
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public String execute(TaskList taskList, VenueList venueList, UI ui, Storage storage) {
        return ui.showTasks(taskList.getTasks());
    }
}
