package arona.commands;

import java.util.ArrayList;

import arona.task.Task;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to search for tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyWord;

    /**
     * Initializes a new FindCommand.
     *
     * @param taskList The task list to search within.
     * @param ui       The user interface for displaying results.
     * @param keyWord  The keyword to search for in task descriptions.
     */
    public FindCommand(TaskList taskList, Ui ui, String keyWord) {
        super(taskList, ui);
        this.keyWord = keyWord;
    }

    /**
     * Executes the FindCommand to search for tasks containing the specified keyword.
     */
    @Override
    public void execute() {
        TaskList result = filterTasksByKeyword(taskList, keyWord);
        ui.showSearchResult(result);
    }

    /**
     * This method searches through the descriptions of tasks in the provided TaskList
     * and adds tasks to a new TaskList if their descriptions contain the specified keyword.
     *
     * @param taskList The TaskList to search for matching tasks.
     * @param keyWord  The keyword to search for in task descriptions.
     * @return A TaskList containing the matching tasks.
     */
    public static TaskList filterTasksByKeyword(TaskList taskList, String keyWord) {
        ArrayList<Task> tasks = taskList.getTasks();
        TaskList result = new TaskList();

        for (Task task : tasks) {
            String[] descriptionTokens = task.getDescription().split("\\s+");

            for (String word : descriptionTokens) {
                if (word.equals(keyWord)) {
                    result.getTasks().add(task);
                    break;
                }
            }
        }
        return result;
    }
}
