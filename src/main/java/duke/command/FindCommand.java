package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * The FindCommand class represents a command to search for tasks containing a specified keyword.
 * Upon execution, it searches the task list for tasks that match the given keyword and displays the results.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand object with the specified keyword to search for.
     *
     * @param keyword The keyword to search for within the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by searching for tasks that match the specified keyword
     * within the task list and displaying the matching tasks.
     *
     * @param list The task list containing tasks to search within.
     * @param ui The user interface for displaying messages.
     * @param storage The storage manager for reading and saving task data.
     * @throws DukeException If there is an error while executing the command.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> resultList = list.findTask(this.keyword);
        ui.showListMatching(resultList);
    }

    /**
     * Indicates whether the FindCommand is an exit command.
     * This method always returns false, as the FindCommand is not an exit command.
     *
     * @return False, indicating that the command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
