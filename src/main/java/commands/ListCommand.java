package commands;

import ui.UI;
import data.Actions;
import duke.DukeException;

public class ListCommand extends Command {
    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        ui.lineSandwich(actionList.stringList());
    }
}
