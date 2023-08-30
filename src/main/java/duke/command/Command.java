package duke.command;

import duke.exception.DukeException;
import duke.taskList.TaskList;
import duke.UI.UI;
import duke.storage.Storage;

import java.io.IOException;


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
