package jelly.commands;

import jelly.main.Storage;
import jelly.main.TaskList;
import jelly.main.Ui;

public abstract class Command {
    public boolean isRunning() {
        return true;
    }
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
