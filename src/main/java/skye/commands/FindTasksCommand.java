package skye.commands;

import java.util.List;

import skye.data.TaskList;
import skye.data.VenueList;
import skye.data.task.Task;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents the command to find tasks containing the keyword specified by the user.
 */
public class FindTasksCommand extends FindCommand {
    public static final String RESOURCE = "tasks";

    public FindTasksCommand(String keyword) {
        super(keyword);
    }

    /**
     * Execute the find tasks command and returns a list of tasks containing the keyword in
     * the description.
     *
     * @param taskList TaskList
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public String execute(TaskList taskList, VenueList venueList, UI ui, Storage storage) {
        List<Task> tasks = taskList.findTasksContaining(getKeyword());
        return ui.showFoundTasks(tasks);
    }
}
