package rua.command;

import rua.task.TaskList;
import rua.common.*;

public class ClearCommand implements Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showMessage("Task list cleared.\n");
        tasks = new TaskList();
        storage.save(tasks);
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof ClearCommand;
    }
}