package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command to find tasks in the task list based on a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Initializes a FindCommand with the specified keyword to search for.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find tasks in the task list based on the keyword and returns a message.
     *
     * @param taskList The task list containing tasks.
     * @param storage  The storage component for saving tasks.
     * @param ui       The user interface for displaying messages.
     * @return A message indicating the tasks that match the keyword.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        TaskList matchingTasks = taskList.findTask(keyword);
        return ui.findTaskMessage(matchingTasks, keyword);
    }
}

