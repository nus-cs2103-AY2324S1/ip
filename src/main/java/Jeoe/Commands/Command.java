package Jeoe.Commands;

import Jeoe.Tasks.TaskManager;
import Jeoe.Others.Ui;
import Jeoe.Others.StorageManager;

public abstract class Command {
    boolean isExit;
    Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskManager taskManager, Ui ui, StorageManager storageManager);

    public boolean isExit() {
        return this.isExit;
    }
}
