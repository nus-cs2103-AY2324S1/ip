package buddy;

import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException;
}
