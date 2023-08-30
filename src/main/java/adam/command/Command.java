package adam.command;

import adam.Storage;
import adam.Ui;
import adam.TaskList;

public interface Command {
    public void execute(TaskList tasks, Storage storage, Ui ui);
}
