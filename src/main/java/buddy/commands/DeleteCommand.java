package buddy.commands;

import buddy.Task;
import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for deleting a task from the task list.
 */
public class DeleteCommand extends Command {
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        Task deletedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.printDeleteSuccessMessage(deletedTask, tasks);
        storage.writeToFile(tasks.getAllTasks());
    }
}
