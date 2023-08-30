package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {
    public ListCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    @Override
    public void execute() {
        ui.showList(tasks);
    }
}
