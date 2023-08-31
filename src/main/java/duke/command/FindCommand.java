package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * Represents a command to find all tasks containing a keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs an FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by finding all tasks containing the keyword.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     * @throws DukeException If the provided task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.find(keyword);
        ui.printTaskList(filteredTasks, "Here are the matching tasks in your list:");
    }
}
