package bareum.commands;

import bareum.BareumException;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, Storage storage, TaskList taskList) throws BareumException;
}
