package commands;

import ui.UI;
import data.Actions;

public class ByeCommand extends Command {
    @Override
    public void executeCommand(UI ui, Actions actionList) {
        ui.lineSandwich(" Finally, I can rest.");
    }

    @Override
    public boolean exit() {
        return true;
    }
}
