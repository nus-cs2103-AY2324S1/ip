package rua.command;

import rua.common.Ui;
import rua.common.Storage;
import rua.task.TaskList;

public interface Command {
    TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    boolean isExit();

    @Override
    boolean equals(Object o);
}
