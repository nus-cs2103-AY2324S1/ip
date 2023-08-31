package bongo.command;

import bongo.task.TaskList;
import bongo.helper.BongoException;
import bongo.helper.Ui;
import bongo.helper.Storage;

public abstract class Command {

    boolean isExit;

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws BongoException;

    public Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return false;
    }
}
