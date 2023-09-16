package bob.data.command;

import bob.data.exception.DukeException;
import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;
public abstract class Command {
    public Command() {}
    public abstract String execute(Storage storage, TaskList list, Ui ui) throws DukeException;
}
