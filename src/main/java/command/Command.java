package command;

import task.TaskList;
import main.UI;
import main.Storage;

import exception.DukeException;

public abstract class Command {

    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {}

    public boolean end() {
        return true;
    }
}
