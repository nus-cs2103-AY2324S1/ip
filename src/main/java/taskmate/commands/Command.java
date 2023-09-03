package taskmate.commands;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;

public abstract class Command {

    boolean isExit;
    String commandType;

    String getType() {
        return this.commandType;
    }
    public boolean getIsExit() {
        return this.isExit;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public String getCommandType() {
        return this.commandType;
    }

    @Override
    public String toString() {
        return this.getType();
    }
}
