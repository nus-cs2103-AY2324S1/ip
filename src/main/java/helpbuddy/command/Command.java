package helpbuddy.command;

import java.io.IOException;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.storage.Storage;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

public abstract class Command {
    private boolean exit = false;
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, HelpBuddyException;

    public boolean isExit() {
        return this.exit;
    }

    public void toggleExit() {
        this.exit = !this.exit;
    }
}
