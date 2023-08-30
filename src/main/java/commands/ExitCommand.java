package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
