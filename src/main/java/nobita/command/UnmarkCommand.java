package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.Task;
import nobita.task.TaskList;
import nobita.ui.Ui;

public class UnmarkCommand extends Command{
    private final int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException {
        if (!tasks.checkIndexWithinRange(index)) {
            throw new NobitaException("Selected task number not in list");
        }
        Task task = tasks.markIncomplete(index);
        ui.showMessage("OK, I've marked this task as not done yet:\n" + task);
    };

    @Override
    public boolean isExit() {
        return false;
    };
}
