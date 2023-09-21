package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The ListCommand class represents a command to list all tasks in the Duke application.
 * It extends the Command class and is responsible for handling the execution of the command.
 */
public class ListCommand extends Command {

    /**
     * Executes the "list" command by displaying a sorted list of all tasks in the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface for displaying the list of tasks.
     * @param storage The storage for saving the updated task list to a file.
     * @return A string containing the sorted list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortTasksByPriority();
        return ui.showTaskList(tasks.getAllTasks());
    }
}
