package didier.command;

import didier.Storage;
import didier.TaskList;
import didier.UI;
import didier.exception.DidierException;
import didier.task.Task;

/**
 * The FindCommand encapsulates a command that searches the task list in the storage for tasks
 * that match the given keyword and outputs the appropriate tasks to the users.
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
