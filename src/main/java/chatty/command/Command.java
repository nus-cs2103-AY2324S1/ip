package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

public abstract class Command {

    private boolean isExit;

    public Command(Boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException;
}
