package command;

import task.TaskList;
import main.UI;
import main.Storage;

import exception.DukeException;

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
