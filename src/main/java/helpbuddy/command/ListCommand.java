package helpbuddy.command;

import java.io.IOException;

import helpbuddy.storage.Storage;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.printListMessage(taskList);
    }
}
