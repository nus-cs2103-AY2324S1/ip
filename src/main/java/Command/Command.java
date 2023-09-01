package Command;

import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;

public abstract class Command {

    protected boolean isExit = false;
    public boolean isExit() {
        return isExit;
    }
    public abstract void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException;
}
