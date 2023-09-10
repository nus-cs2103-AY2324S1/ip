package tasket.command;

import tasket.storage.Storage;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.ui.Ui;

public abstract class Command {
    protected String commandDescription;

    public Command(String description) {
        this.commandDescription = description;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException;

    public boolean isExit() {
        return false;
    }
}
