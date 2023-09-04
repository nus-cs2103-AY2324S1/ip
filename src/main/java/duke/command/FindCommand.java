package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The FindCommand class represents a command for searching and finding tasks
 * based on user-defined criteria in a task management application.
 *
 * @author selwyn
 */
public class FindCommand extends Command {

    /** The search criteria provided by the user for finding tasks. */
    private String taskToFindDetails;

    /**
     * Constructs a new FindCommand object with the specified search criteria.
     *
     * @param args The search criteria provided by the user.
     */
    public FindCommand(String args) {
        this.taskToFindDetails = args;
    }

    /**
     * Executes the find command, searching for tasks in the given task list
     * that match the provided search criteria and displays the results.
     *
     * @param taskList The list of tasks to search within.
     * @param ui       The user interface for displaying the results.
     * @param storage  The storage for data retrieval and persistence.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = taskList.findTasks(taskToFindDetails);
        TaskList taskListWithFoundTasks = new TaskList(foundTasks);
        ui.printTaskList(taskListWithFoundTasks, true);
    }
}
