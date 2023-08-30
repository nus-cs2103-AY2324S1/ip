package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ListCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.print();
    }
}
