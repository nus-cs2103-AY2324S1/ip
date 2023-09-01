package command;

import duke.Storage;
import duke.Ui;

import java.util.ArrayList;

import task.Task;
import task.TaskList;

/**
 * Prints a filtered todo list according to the keyword in input
 */
public class FindCommand extends Command {

    /** Keyword to filter the todo list */
    protected String keyword;
    public static final String COMMAND_WORD = "find";

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Prints a filtered TaskList which contains tasks whose contains the
     * given keyword
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param ui Text Ui that the user interacts with
     * @param storage File path where the tasks are stored
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.filterTasks(this.keyword);
        if (filteredTasks.isEmpty()) {
            ui.showMessage("No matching tasks found");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            ui.printTaskList(filteredTasks);
        }
    }
}
