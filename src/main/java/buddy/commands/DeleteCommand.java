package buddy.commands;

import buddy.Task;
import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

public class DeleteCommand extends Command {
    private int index;

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
