package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;
import duke.exceptions.DukeException;

public abstract class Command {
    abstract public void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
