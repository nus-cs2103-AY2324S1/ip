package commands;

import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.Ui;

public abstract class Command {
    public abstract void execute(
            TaskList tasks, Storage storage, Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
