package commands;

import data.TaskList;
import data.exception.DukeException;
import storage.Storage;
import ui.UiCli;
import ui.UiMessage;

/**
 * The Command abstract class.
 * 
 * Represents the logic that is to be executed
 * for the respective user command.
 */
public abstract class Command {
    public abstract UiMessage execute(
            TaskList tasks, Storage storage, UiCli uiCli) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
