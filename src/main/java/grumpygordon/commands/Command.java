package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.ui.Ui;
import grumpygordon.tasks.*;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}
