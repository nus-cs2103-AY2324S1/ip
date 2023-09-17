package carbonbot.command;

import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.exception.CarbonException;
import carbonbot.exception.CarbonInvalidIndexException;
import carbonbot.task.Task;

/**
 * The command deletes a task from the task list and saves the list to disk.
 */
public class DeleteCommand extends Command {
    private final int taskIdx;

    /**
     * Constructs a DeleteCommand object that will delete the task at the provided index
     *
     * @param taskIdx Task index to be deleted from the list
     */
    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CarbonException {
        try {
            Task task = tasks.get(taskIdx);
            tasks.delete(taskIdx);

            ui.bufferMessage("Noted. I've removed this task:");
            ui.bufferMessage(task.toString());
            ui.bufferMessage("Now you have " + tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException ie) {
            throw new CarbonInvalidIndexException();
        }

        storage.saveTasks(tasks);
    }
}
