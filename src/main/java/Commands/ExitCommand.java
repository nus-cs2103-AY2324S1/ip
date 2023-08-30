package Commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ExitCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}
