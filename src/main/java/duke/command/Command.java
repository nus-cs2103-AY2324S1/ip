package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

public abstract class Command {

    public abstract void execute(TaskList taskList, Storage storage, Ui ui);
}
