package bongo.command;

import bongo.task.TaskList;
import bongo.helper.Ui;
import bongo.helper.Storage;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
