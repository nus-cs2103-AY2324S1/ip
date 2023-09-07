package command;

import duke.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to find tasks that match a specified keyword in their descriptions.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Initializes a new instance of the FindCommand class with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the "find" command to search for tasks containing the specified keyword
     * in their descriptions and displays the matching tasks.
     *
     * @param taskList   The task list to search for matching tasks.
     * @param ui      The user interface to display the matching tasks.
     * @param storage The storage system (not used in this command).
     * @return A message displaying the list of tasks that match the specification
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.showMatchingTasks(taskList.findTasks(keyword));
    }
}
