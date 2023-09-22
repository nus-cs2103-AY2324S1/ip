package commands;

import data.Actions;
import duke.DukeException;
import ui.UI;

public class ListCommand extends Command {
    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        ui.lineSandwich(actionList.stringList());
    }
}
