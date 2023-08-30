package Commands;

import TaskList.TaskList;
import UI.UI;
import Storage.Storage;

public interface Command {
    void execute(TaskList tasks, UI ui, Storage storage);
}
