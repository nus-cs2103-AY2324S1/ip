package Command;
import Ui.*;
import Duke.*;
import TaskList.TaskList;
import Storage.Storage;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
