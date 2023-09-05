package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents a command to find tasks that match a given keyword in the task list.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.findTasks(this.keyword, ui);
    }
}
