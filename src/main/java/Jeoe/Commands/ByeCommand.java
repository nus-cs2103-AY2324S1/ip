package Jeoe.Commands;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.TaskManager;

public class ByeCommand extends Command {
    ByeCommand(String input) {
        super(true);
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        ui.showEndString();
    }
}
