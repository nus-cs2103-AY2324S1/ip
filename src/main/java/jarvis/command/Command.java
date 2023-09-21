package jarvis.command;

import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.JarvisException;

public abstract class Command {
    protected boolean isExit = false;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException;

    public boolean isExit() {
        return isExit;
    }
}