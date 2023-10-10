package skye.commands;

import java.util.List;

import skye.data.ListManager;
import skye.data.TaskList;
import skye.data.task.Task;
import skye.storage.StorageManager;
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
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     */
    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager) {
        TaskList taskList = listManager.getTaskList();
        List<Task> tasks = taskList.findTasksContaining(getKeyword());
        return ui.showFoundTasks(tasks);
    }
}
