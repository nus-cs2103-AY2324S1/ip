package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
