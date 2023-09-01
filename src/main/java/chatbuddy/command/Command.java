package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.TaskList;
import chatbuddy.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException;

    public boolean isExit() {
        return false;
    }
}
