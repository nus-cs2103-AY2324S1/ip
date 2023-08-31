package helpbuddy.command;

import helpbuddy.storage.Storage;
import helpbuddy.ui.Ui;
import helpbuddy.task.TaskList;

import java.io.IOException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        ui.printListMessage(taskList);
    }
}
