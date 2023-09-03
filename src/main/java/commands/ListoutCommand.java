package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class ListoutCommand extends Command {

    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.listout(tasks);
    }

}
