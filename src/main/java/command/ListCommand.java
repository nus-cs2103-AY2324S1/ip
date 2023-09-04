package command;

import task.TaskList;
import main.Storage;
import main.UI;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.list(taskList.getTaskArrayList());
    }

}
