package Command;
import Ui.*;
import Duke.*;
import TaskList.TaskList;
import Storage.Storage;
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}