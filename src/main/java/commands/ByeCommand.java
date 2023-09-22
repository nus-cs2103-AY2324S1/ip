package commands;

import data.Actions;
import ui.UI;

public class ByeCommand extends Command {
    @Override
    public void executeCommand(UI ui, Actions actionList) {
        ui.lineSandwich(" Finally, I can rest.");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
