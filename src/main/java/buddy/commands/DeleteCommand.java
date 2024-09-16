package buddy.commands;

import buddy.Task;
import buddy.TaskList;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for deleting a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String MESSAGE_FORMAT =
            "delete <task index>\n" + "Example: delete 2";
    public static final String MESSAGE_INDEX_BOUND =
            "Index from 1 to ";
    private int index;

    /**
     * The constructor for a DeleteCommand.
     *
     * @param index The zero-based index of the task.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task deletedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        String response = ui.printDeleteSuccessMessage(deletedTask, tasks);
        storage.writeToFile(tasks.getAllTasks());
        return response;
    }
}
