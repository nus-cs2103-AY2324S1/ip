package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks containing a specified keyword in the task list.
 */
public class FindCommand implements Command {

    private static String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the task list.
     */
    public FindCommand(String keyword) {
        FindCommand.keyword = keyword;
    }

    /**
     * Executes the find command on the provided task list, displaying tasks that match the keyword.
     *
     * @param tasks   The task list to search within.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage manager to handle data storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.find(keyword);
    }
}
