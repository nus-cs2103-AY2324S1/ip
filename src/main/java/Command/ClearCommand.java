package Command;
import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;

public class ClearCommand extends Command{
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        fileStorage.write(tasklist.clear());
        ui.showClearTask();
    }
}
