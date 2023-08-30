package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    protected TaskList tasks;
    protected Ui ui;
    protected Storage storage;

    public Command(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    public abstract void execute();
}
