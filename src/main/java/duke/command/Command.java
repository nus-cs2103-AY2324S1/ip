package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws Exception;
}
