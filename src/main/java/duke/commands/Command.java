package duke.commands;

import java.io.IOException;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.exception.DukeException;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
    public boolean isExit() {
        return false;
    }
}
