package commands;

import exceptions.StorageException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws StorageException;

    public abstract boolean isExit();
}
