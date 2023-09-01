package aichan.command;

import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // no interaction with tasks
        ui.showMessage("Ta-da! It's time to go~ Keep smiling till we reunite!");
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
