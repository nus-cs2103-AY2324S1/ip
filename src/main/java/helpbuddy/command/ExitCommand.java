package helpbuddy.command;

import java.io.IOException;

import helpbuddy.storage.Storage;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.saveData(taskList);
        super.toggleExit();
        ui.printByeMessage();
        ui.closeScanner();
    }
}
