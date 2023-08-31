package eva.command;

import eva.task.TaskList;
import eva.Ui;
import eva.Storage;
import eva.DukeException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}

