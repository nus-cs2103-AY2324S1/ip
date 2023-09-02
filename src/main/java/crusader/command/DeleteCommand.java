package crusader.command;

import crusader.exception.CrusaderException;
import crusader.task.Task;
import crusader.TaskList;
import crusader.Ui;

public class DeleteCommand extends Command {
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws CrusaderException {
        Task deletedTask = taskList.deleteTask(index);
        ui.say(String.format(
                "Deleting the task:\n%s\nNow there are %d tasks in the list.",
                deletedTask.toString(),
                taskList.getSize()));
    }
}
