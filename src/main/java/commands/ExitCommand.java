package commands;

import functions.*;

public class ExitCommand extends Command {

    public ExitCommand() {}

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
