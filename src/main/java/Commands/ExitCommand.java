package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ExitCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}
