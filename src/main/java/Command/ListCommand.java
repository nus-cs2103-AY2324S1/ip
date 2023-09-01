package Command;

import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;

public class ListCommand extends Command{

    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) {
        ui.showList(tasklist);
    }
}
