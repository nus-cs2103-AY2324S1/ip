package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.Task;
import nobita.task.TaskList;
import nobita.ui.Ui;

public class DeleteCommand extends Command{
    private final int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException {
        if (!tasks.checkIndexWithinRange(index)) {
            throw new NobitaException("Selected task number not in list");
        }
        Task task = tasks.deleteTask(index);
        ui.showMessage("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.getTotalTask() +" tasks in the list.");
    };

    @Override
    public boolean isExit() {
        return false;
    };
}
