package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;


public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
