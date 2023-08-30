package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand(TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
    }

    @Override
    public void execute() {
        ui.showExit();
    }

}
