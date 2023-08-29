package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.ui.Ui;
import grumpygordon.tasks.*;

public class ByeCommand extends Command {
    public ByeCommand() {
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showOutroMessage();
    }
}
