package Command;

import Task.TaskList;
import Main.UI;
import Main.Storage;

import Exception.DukeException;

public class ByeCommand extends Command {

    public ByeCommand() {}
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        ui.byeMessage();
        storage.saveList(taskList);
    }

    @Override
    public boolean end() {
        return false;
    }
}
