package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Task;
import robert.task.TaskList;
import robert.ui.Ui;

/**
 * A Delete extension of the <tt>Command</tt> class. Deletes a particular task
 * from the list of tasks.
 *
 * @author Lee Zhan Peng
 */
public class DeleteCommand extends Command {

    /** The index of task to be deleted */
    private final int taskIndex;

    /**
     * Constructs DeleteCommand using the index of the task.
     *
     * @param taskIndex the index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the removal of task.
     *
     * @param tasks the list of tasks to be added onto.
     * @param ui the ui that is responsible for the output of the CLI.
     * @param storage the storage that loads stored tasks from hard disk.
     * @throws RobertException if index is out of bounds of the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        Task deletedTask = tasks.deleteTask(taskIndex);
        ui.showMessage("Noted. I've removed this task:\n  " + deletedTask
                + "\nNow you have " + tasks.getTaskCount() + " "
                + (tasks.getTaskCount() > 1 ? "tasks" : "task")
                + " in the list.");
    }
}
