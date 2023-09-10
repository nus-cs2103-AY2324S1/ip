package corgi.commands;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;

/**
 * Represents a command to list tasks in the task list.
 * This command retrieves and displays the list of tasks to the user.
 */
public class ListTasksCommand extends Command {
    /**
     * Initializes a new ListTasksCommand instance.
     */
    public ListTasksCommand() {
        super(false);
    }

    /**
     * Executes the command by retrieving and displaying the list of tasks to the user.
     * It returns either the list of tasks or a message indicating that no tasks are in the list.
     *
     * @param list The task list to be displayed.
     * @param renderer The text renderer to return formatted message.
     * @param storage The storage for saving and loading tasks (not used in this command).
     */
    @Override
    public String execute(TaskList list, TextRenderer renderer, Storage<Task> storage) {
        if (list.isEmpty()) {
            return renderer.showNoTaskFound();
        } else {
            return renderer.showTaskList(list.toString());
        }

    }
}
