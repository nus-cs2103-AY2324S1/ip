package Jelly.commands;

import Jelly.main.Storage;
import Jelly.main.TaskList;
import Jelly.main.Ui;

public abstract class Command {
    public boolean isRunning() {
        return true;
    }
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
