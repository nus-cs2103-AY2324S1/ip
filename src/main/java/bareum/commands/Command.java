package bareum.commands;

import bareum.BareumException;
import bareum.Storage;
import bareum.TaskList;
import bareum.Ui;

/**
 * A command related to tasks.
 */

public abstract class Command {
    public abstract String execute(Ui ui, Storage storage, TaskList taskList) throws BareumException;
}
