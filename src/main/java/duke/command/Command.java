package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
