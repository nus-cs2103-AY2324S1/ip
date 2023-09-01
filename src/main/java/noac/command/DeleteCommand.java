package noac.command;

import noac.NoacException;
import noac.Storage;
import noac.TaskList;
import noac.Ui;

/**
 * For executing the delete command.
 */
public class DeleteCommand  extends Command {

    private int taskIndex;

    /**
     * Create the delete command with the which task to delete.
     *
     * @param taskIndex which task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Delete the task and update the Tasklist and save file
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     * @throws NoacException For any errors that needs to be displayed to user.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoacException {

        if (taskIndex + 1 > tasks.size() ||  taskIndex < 0) {
            throw new NoacException("☹ OOPS!!! Please enter a task in your list!");
        }

        tasks.deleteTask(this.taskIndex);

        ui.showDeleteTask(tasks.getTask(this.taskIndex), tasks.size());

        storage.save(tasks);

    }
}
