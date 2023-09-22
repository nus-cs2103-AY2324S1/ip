package commands;

import data.Actions;
import duke.DukeException;
import ui.UI;

public abstract class Command {
    public abstract void executeCommand(UI ui, Actions actionList) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
