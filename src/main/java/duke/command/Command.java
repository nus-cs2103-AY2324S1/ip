package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();

}
