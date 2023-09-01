package Commands;

import Others.StorageManager;
import Others.Ui;
import Tasks.TaskManager;

public class ByeCommand extends Command {
    ByeCommand(String input) {
        super(true);
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        ui.showEndString();
    }
}
