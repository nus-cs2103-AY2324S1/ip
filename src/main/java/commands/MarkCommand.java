package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(TaskList tasks, Ui ui, Storage storage, int index) {
        super(tasks, ui, storage);
        this.index = index;
    }

    @Override
    public void execute() {
        tasks.markTask(index);
        ui.showMarkedTask(tasks, index);
        storage.save(tasks);
    }
}
