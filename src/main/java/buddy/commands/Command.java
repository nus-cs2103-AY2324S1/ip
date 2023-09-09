package buddy.commands;

import buddy.TaskList;
import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

public abstract class Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        throw new BuddyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    };
}
