package aichan.command;

import aichan.AiChanException;
import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Task;

/**
 * Represents a command to mark a task as undone.
 * Inherits from the Command class.
 */
public class UnmarkCommand extends Command {
    private int taskId;

    /**
     * Constructs a UnmarkCommand object with task ID.
     *
     * @param taskId The ID of task to be marked as undone.
     */
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Marks the task as undone, informs user, records in the file.
     *
     * @param tasks A list of task.
     * @param ui User interface to show message.
     * @param storage Storage storing the tasks' description.
     * @throws AiChanException If the task number is invalid.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AiChanException {
        int size = tasks.getSize();
        if (taskId < 1 || taskId > size) throw new AiChanException("Please provide a valid task number.");
        Task task = tasks.getTask(taskId);
        task.unmark();
        ui.showMessage("OK, I've marked this task as not done yet:\n"
                + task.toString());
        storage.save(tasks);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
