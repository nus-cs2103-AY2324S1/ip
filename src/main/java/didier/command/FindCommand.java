package didier.command;

import didier.Storage;
import didier.TaskList;
import didier.UI;
import didier.exception.DidierException;
import didier.task.Task;

/**
 * The FindCommand encapsulates the logic of what occurs when the user searches their task list
 * for tasks that match a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for the FindCommand object.
     *
     * @param keyword The keyword to look for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DidierException {
        TaskList taskListFind = new TaskList();
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.descriptionHasKeyword(this.keyword)) {
                taskListFind.addTask(task);
            }
        }
        ui.botPrintTaskKeywordList(taskListFind);
    }
}
