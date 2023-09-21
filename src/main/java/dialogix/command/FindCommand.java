package dialogix.command;

import java.util.ArrayList;

import dialogix.exception.DialogixException;
import dialogix.main.Storage;
import dialogix.main.TaskList;
import dialogix.main.Ui;
import dialogix.task.Task;

/**
 * Represents a command to find tasks in the task list matching a given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the keyword to search for in tasks.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by searching for tasks matching the given keyword and displaying the results.
     *
     * @param taskList The task list.
     * @param ui       The user interface.
     * @param storage  The storage manager.
     * @throws DialogixException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DialogixException {
        ArrayList<Task> matchingTasks = taskList.find(keyword);
        ui.showSearchResults(matchingTasks);
        ui.printList(matchingTasks, "Here are the matching tasks that I can find");
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
