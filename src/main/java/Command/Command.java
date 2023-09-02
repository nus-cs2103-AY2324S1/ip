package Command;

import Task.TaskList;
import Main.UI;
import Main.Storage;

import Exception.DukeException;

public abstract class Command {

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {}

    public boolean end() {
        return true;
    }
}
