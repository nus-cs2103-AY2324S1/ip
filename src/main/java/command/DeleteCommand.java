package command;

import exception.DialogixException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class DeleteCommand extends Command {
    private int zeroBasedIndex;

    public DeleteCommand(int oneBasedIndex) {
        this.zeroBasedIndex = oneBasedIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DialogixException("OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }
        tasks.addToStack();
        Task deletedTask = tasks.get(zeroBasedIndex);
        tasks.delete(zeroBasedIndex);
        ui.printDeleteSuccessMessage(deletedTask, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}
