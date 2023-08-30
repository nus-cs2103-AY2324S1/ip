package commands;

import services.Storage;
import services.TaskList;
import services.UI;

public abstract class Command {

    public abstract void execute(TaskList tasks, UI ui, Storage storage);

    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
