package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;

/**
 * Abstract parents class of Commands that can be created by users.
 */
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
