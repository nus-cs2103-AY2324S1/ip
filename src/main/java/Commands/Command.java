package Commands;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;

public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage);
}
