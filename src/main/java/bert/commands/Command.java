package bert.commands;

import bert.storage.Storage;
import bert.tasks.TaskList;
import bert.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit() {
        return false;
    }
}
