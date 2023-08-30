package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

public abstract class Command {
    final String s;

    protected Command(String s) {
        this.s = s;
    }

    public abstract void execute(TaskList lst, UI ui, Storage s) throws DukeException;
    public boolean isExit() {
        return false;
    }
}
