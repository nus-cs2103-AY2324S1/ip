package didier.command;

import didier.Storage;
import didier.TaskList;
import didier.exception.DidierException;
import didier.task.Task;

/**
 * The FindCommand encapsulates the logic of what occurs when the user searches their task list
 * for tasks that match a given keyword.
 */
public class FindCommand extends Command {
    private String keyword;
    private TaskList taskListFind;

    /**
     * Constructor for the FindCommand object.
     *
     * @param keyword The keyword to look for in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.taskListFind = new TaskList();
    }
    @Override
    public void execute(TaskList taskList, Storage storage) throws DidierException {
        for (int i = 1; i <= taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            if (task.descriptionHasKeyword(this.keyword)) {
                taskListFind.addTask(task);
            }
        }
    }

    @Override
    public String getBotOutput(TaskList taskList, Storage storage) throws DidierException {
        String outputText = "";
        outputText += "The tasks that match the keyword in your lists are as follows:";
        for (int i = 1; i <= taskListFind.getSize(); i++) {
            outputText += String.format("\n%d.%s", i, taskListFind.getTask(i));
        }
        return outputText;
    }
}
