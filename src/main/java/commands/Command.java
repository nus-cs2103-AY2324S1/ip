package commands;

import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.UI;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException;
    public boolean isExit() {
        return false;
    }
}
