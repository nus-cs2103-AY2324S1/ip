package crusader.command;

import crusader.Storage;
import crusader.TaskList;
import crusader.exception.CrusaderException;
import crusader.task.Task;

/**
 * Command used to delete tasks from the bot.
 */
public class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws CrusaderException {
        Task deletedTask = taskList.deleteTask(index);
        storage.saveTasks(taskList.getTasks());
        return String.format(
                "Deleting the task:\n%s\nNow there are %d tasks in the list.",
                deletedTask.toString(),
                taskList.getSize());
    }
}
