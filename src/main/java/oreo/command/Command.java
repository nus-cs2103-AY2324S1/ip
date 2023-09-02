package oreo.command;

import oreo.task.TaskList;
import oreo.ui.Ui;

public abstract class Command {
    public abstract void execute(Ui ui, TaskList tasks);

    public boolean isExit() {
        return this instanceof ByeCommand;
    }
}
