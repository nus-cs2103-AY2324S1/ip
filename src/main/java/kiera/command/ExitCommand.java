package kiera.command;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
