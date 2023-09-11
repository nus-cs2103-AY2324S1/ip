package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showUnknownCommand();
    }
}
