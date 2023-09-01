package commands;

import storage.Storage;
import data.TaskList;
import data.exception.DukeException;
import ui.Ui;

/**
 * The Command abstract class.
 * 
 * Represents the logic that is to be executed
 * for the respective user command.
 */
public abstract class Command {
    abstract public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
