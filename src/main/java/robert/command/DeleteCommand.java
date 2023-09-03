package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Task;
import robert.task.TaskList;

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
     * @param storage the storage that loads stored tasks from hard disk.
     * @return String of output message.
     * @throws RobertException if index is out of bounds of the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RobertException {
        Task deletedTask = tasks.deleteTask(taskIndex);
        return "Noted. I've removed this task:\n  " + deletedTask
                + "\nNow you have " + tasks.getTaskCount() + " "
                + (tasks.getTaskCount() > 1 ? "tasks" : "task")
                + " in the list.";
    }
}
