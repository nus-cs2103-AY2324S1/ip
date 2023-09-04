package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public abstract class Command {

    public Command() {
    }
    public abstract void execute(Storage storage, Ui ui, TaskList taskList);
}
