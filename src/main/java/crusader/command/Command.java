package crusader.command;

import crusader.exception.CrusaderException;
import crusader.TaskList;
import crusader.Ui;

public abstract class Command {
    public Command() {
        // empty
    }

    public abstract void execute(Ui ui, TaskList taskList) throws CrusaderException;

    public boolean isExit() {
        return false;
    }
}
