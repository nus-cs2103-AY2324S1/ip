package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;

public abstract class Command {

    protected boolean isExit;

    public Command() {
        this.isExit = false;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return this.isExit;
    }
}
