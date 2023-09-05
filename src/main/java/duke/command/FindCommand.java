package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The FindCommand class represents a command for finding tasks that match a search query.
 *
 * @author selwyn
 */
public class FindCommand extends Command {
    private String taskToFindDetails;

    /**
     * Constructs a FindCommand with the specified search query.
     *
     * @param args The search query for finding tasks.
     */
    public FindCommand(String args) {
        this.taskToFindDetails = args;
    }

    /**
     * Executes the FindCommand by searching for tasks that match the given query.
     *
     * @param taskList The task list to search within.
     * @param storage  The storage object (not used in this command).
     * @return A message displaying the tasks that match the search query.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        ArrayList<Task> foundTasks = taskList.findTasks(taskToFindDetails);
        TaskList taskListWithFoundTasks = new TaskList(foundTasks);
        return Ui.printTaskList(taskListWithFoundTasks, true);
    }
}
