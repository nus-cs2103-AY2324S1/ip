package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ExitCommand extends Command{
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.exit(taskList, storage);
    }
}
