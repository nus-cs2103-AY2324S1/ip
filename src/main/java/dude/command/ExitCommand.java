package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showFarewell();
    }
}
