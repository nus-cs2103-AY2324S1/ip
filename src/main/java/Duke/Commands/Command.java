package Duke.Commands;

import Duke.Tools.Storage;
import Duke.Tools.TaskList;
import Duke.Tools.Ui;

abstract public class Command {

    protected String fullCommand;
    protected boolean isExit;

    public Command(String fullCommand) {
        this.fullCommand = fullCommand;
        this.isExit = false;
    }

    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public boolean isExit() {
        return this.isExit;
    }

}
