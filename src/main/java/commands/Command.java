package commands;

import ui.UI;
import data.Actions;
import duke.DukeException;

public abstract class Command {
    public abstract void executeCommand(UI ui, Actions actionList) throws DukeException;

    public boolean exit(){
        return false;
    }
}
