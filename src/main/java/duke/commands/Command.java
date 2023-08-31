package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException, IOException;
    public boolean isExit() {
        return false;
    }
}