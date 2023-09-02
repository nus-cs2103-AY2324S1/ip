package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;

import duck.task.TaskList;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.unmark(index);
        ui.showUnmarkTaskMessage(tasks.getTask(index));
        storage.updateTasks(tasks);
    }
}
