package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.exception.DreException;
import dre.task.TaskList;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DreException;

    public boolean isExit() {
        return false;
    }
}
