package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;

import duck.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DuckException;

    // Only ExitCommand should override and return true
    public boolean isExit() {
        return false;
    };
}
