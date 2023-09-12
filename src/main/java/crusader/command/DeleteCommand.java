package crusader.command;

import crusader.TaskList;
import crusader.Ui;
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
    public String execute(Ui ui, TaskList taskList) throws CrusaderException {
        Task deletedTask = taskList.deleteTask(index);
        return String.format(
                "Deleting the task:\n%s\nNow there are %d tasks in the list.",
                deletedTask.toString(),
                taskList.getSize());
    }
}
