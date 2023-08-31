package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

public class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    public boolean isExit() {
        return this.isExit;
    }
}
