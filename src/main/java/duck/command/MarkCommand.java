package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;

import duck.task.TaskList;

public class MarkCommand extends Command {
    int index;

    public MarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        tasks.mark(index);
        ui.showMarkTaskMessage(tasks.getTask(index));
        storage.updateTasks(tasks);
    }
}
