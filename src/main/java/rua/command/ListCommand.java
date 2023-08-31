package rua.command;

import rua.command.Command;
import rua.task.TaskList;
import rua.common.*;

public class ListCommand implements Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showMessage(tasks.toString() + "\n");
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        return o instanceof ListCommand;
    }
}
