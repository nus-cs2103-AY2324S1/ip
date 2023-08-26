package didier.command;

import didier.Storage;
import didier.UI;
import didier.exception.DidierException;
import didier.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DidierException;
    public boolean isExit() {
        return this instanceof ExitCommand;
    }
}
