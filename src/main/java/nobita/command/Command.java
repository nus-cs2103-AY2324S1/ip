package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.TaskList;
import nobita.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException;
    public abstract boolean isExit();
}
