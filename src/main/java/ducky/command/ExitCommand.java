package ducky.command;

import ducky.Storage;
import ducky.TaskList;
import ducky.UserInterface;

public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        ui.showFarewell();
    }
}
