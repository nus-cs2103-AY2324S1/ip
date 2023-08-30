package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
