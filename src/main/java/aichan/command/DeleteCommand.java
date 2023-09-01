package aichan.command;

import aichan.AiChanException;
import aichan.TaskList;
import aichan.Storage;
import aichan.Ui;
import aichan.task.Task;

/**
 * Represents a command to delete a task.
 * Inherits from the Command class.
 */
public class DeleteCommand extends Command{
    private int taskId;

    /**
     * Constructs a DeleteCommand object with task ID.
     *
     * @param taskId The ID of task to be deleted.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Deletes the task from list, informs user, and changes the file.
     *
     * @param tasks A list of task.
     * @param ui User interface to show message.
     * @param storage Storage storing the tasks' description.
     * @throws AiChanException If the task number is invalid.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AiChanException {
        // control the main logic
        // add/delete task, show words
        int size = tasks.getSize();
        if (taskId < 1 || taskId > size) throw new AiChanException("Please provide a valid task number.");
        Task task = tasks.deleteTask(taskId);
        ui.showMessage(String.format("Noted. I've removed this task:\n  %s\n" +
                "Now you have %d tasks in the list", task, size - 1));
        storage.save(tasks);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
