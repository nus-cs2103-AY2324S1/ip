package avalon.command;

import avalon.AvalonException;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

public abstract class Command {
    public abstract String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException;
}
