package rua.command;

import rua.common.Ui;
import rua.common.Storage;
import rua.task.TaskList;


public class ExitCommand implements Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showGoodbye();
        return tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        return o instanceof ExitCommand;
    }
}