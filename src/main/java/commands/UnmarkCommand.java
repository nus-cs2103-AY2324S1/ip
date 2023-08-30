package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(TaskList tasks, Ui ui, Storage storage, int index) {
        super(tasks, ui, storage);
        this.index = index;
    }

    @Override
    public void execute() {
        tasks.unmarkTask(index);
        ui.showUnmark(tasks, index);
    }
}
