package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Initializes a new instance of the FindCommand class with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand, searching for tasks containing the specified keyword and displaying the results.
     *
     * @param taskList The list of tasks to search within.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the task list.
     * @return A list of the matching tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showMatchingTasks(taskList, keyword);
    }
}
