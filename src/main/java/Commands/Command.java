package Commands;

import TaskList.TaskList;
import Ui.Ui;
import Storage.Storage;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage);
}
