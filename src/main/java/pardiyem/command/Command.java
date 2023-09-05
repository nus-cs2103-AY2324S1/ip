package pardiyem.command;

import java.io.IOException;

import pardiyem.storage.Storage;
import pardiyem.task.TaskList;
import pardiyem.ui.Ui;

/**
 * Abstract class to represent a command given by a user
 */
public abstract class Command {
    protected String desc;

    public Command(String desc) {
        this.desc = desc;
    }

    public abstract boolean isExit();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException;
}
