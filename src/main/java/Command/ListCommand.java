package Command;

import Task.TaskList;
import Main.Storage;
import Main.UI;

public class ListCommand extends Command {

    public ListCommand() {}

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.list(taskList.getTaskArrayList());
    }

}
