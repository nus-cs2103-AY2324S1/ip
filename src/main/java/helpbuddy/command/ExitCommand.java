package helpbuddy.command;

import helpbuddy.storage.Storage;
import helpbuddy.ui.Ui;
import helpbuddy.task.TaskList;

import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        storage.saveData(taskList);
        super.toggleExit();
        ui.printByeMessage();
        ui.closeScanner();
    }
}
