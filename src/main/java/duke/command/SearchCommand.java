package duke.command;

import java.util.ArrayList;

import duke.Command;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

// Solution below adapted and inspired from https://chat.openai.com/share/3bd2c2a8-57c4-480a-9055-646c61983a60

/**
 * Represents a command to search for a task in the task list.
 * The SearchCommand is responsible for finding tasks that match the search string.
 */
public class SearchCommand extends Command {
    private String taskToFind;
    private String filters;

    /**
     * Constructs a SearchCommand with the specified keyword.
     *
     * @param taskToFind The keyword to search for in task descriptions.
     */
    public SearchCommand(String taskToFind, String filters) {
        this.taskToFind = taskToFind;
        this.filters = filters;
    }

    // Solution adapted and inspired from https://chat.openai.com/share/b2015678-e886-432d-a802-628f66863c19

    /**
     * Executes the search for tasks containing the specified keyword.
     *
     * @param taskList The TaskList containing all tasks.
     * @param ui       The Ui object for user interface interactions.
     * @param storage  The Storage object for data storage operations.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "Task list cannot be null";
        assert ui != null : "UI object cannot be null";
        assert storage != null : "Storage object cannot be null";

        ArrayList<Task> matchedTasks = new ArrayList<>();
        String keyword = taskToFind.toLowerCase();
        String lowerCaseFilters = filters.toLowerCase();

        for (Task task : taskList.getAllTasks()) {
            if (task.getDescription().contains(keyword)) {
                String lowerCaseTaskType = task.getTaskType().toString().toLowerCase();
                boolean validFilter = lowerCaseFilters.equals(lowerCaseTaskType);
                if (filters.isEmpty() || validFilter) {
                    matchedTasks.add(task);
                }
            }
        }
        if (matchedTasks.isEmpty()) {
            return ui.showErrorMessage("No such task found.");
        } else {
            return ui.displaySearched(matchedTasks);
        }
    }
}
