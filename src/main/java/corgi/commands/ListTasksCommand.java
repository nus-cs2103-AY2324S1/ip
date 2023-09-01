package corgi.commands;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.Ui;

/**
 * Represents a command to list tasks in the task list.
 * This command retrieves and displays the list of tasks to the user.
 */
public class ListTasksCommand extends Command{
    /**
     * Initializes a new ListTasksCommand instance.
     */
    public ListTasksCommand() {
        super(false, CommandType.LIST);
    }

    /**
     * Executes the command by retrieving and displaying the list of tasks to the user.
     * It shows either the list of tasks or a message indicating that no tasks are in the list.
     * 
     * @param list The task list to be displayed.
     * @param ui The user interface for displaying the task list or a message.
     * @param storage The storage for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage<Task> storage) {
        if (list.isEmpty()) {
            ui.showNoTaskInList();
        } else {
            ui.showTaskList(list.toString());
        }

    }
}
