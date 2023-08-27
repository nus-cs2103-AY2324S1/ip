package rua.command;

import rua.common.Ui;
import rua.common.Storage;
import rua.task.TaskList;

public interface Command {
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    boolean isExit();
}
