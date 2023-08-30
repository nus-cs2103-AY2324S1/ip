package brotherman.commands;

import brotherman.storage.*;
import brotherman.tasks.*;
import brotherman.ui.*;


public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
